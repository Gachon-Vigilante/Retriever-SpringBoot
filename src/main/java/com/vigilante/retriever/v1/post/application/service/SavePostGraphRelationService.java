package com.vigilante.retriever.v1.post.application.service;

import static com.vigilante.retriever.v1.post.domain.code.PostErrorCode.*;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vigilante.retriever.common.domain.exception.ConflictException;
import com.vigilante.retriever.v1.channel.application.command.ChannelNeo4jCommand;
import com.vigilante.retriever.v1.channel.application.query.ChannelNeo4jQuery;
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.post.application.command.PostNeo4jCommand;
import com.vigilante.retriever.v1.post.application.query.PostMongoQuery;
import com.vigilante.retriever.v1.post.application.query.PostNeo4jQuery;
import com.vigilante.retriever.v1.post.domain.dto.command.CreatePromotionRelationCommand;
import com.vigilante.retriever.v1.post.domain.entity.PostEntity;
import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;
import com.vigilante.retriever.v1.post.domain.port.in.SavePostGraphRelationUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SavePostGraphRelationService implements SavePostGraphRelationUseCase {

	private final ChannelNeo4jQuery channelNeo4jQuery;
	private final ChannelNeo4jCommand channelNeo4jCommand;
	private final PostNeo4jQuery postNeo4jQuery;
	private final PostNeo4jCommand postNeo4jCommand;
	private final PostMongoQuery postMongoQuery;

	@Override
	@Transactional
	public void createPromotionRelation(CreatePromotionRelationCommand command) {
		Long channelId = command.id();
		String postId = command.postId();
		log.info("[Service] 홍보 관계 생성을 시작합니다. channelId: {}, postId: {}", channelId, postId);

		ChannelGraphView channel = channelNeo4jQuery.findById(channelId)
			.orElseGet(() -> {
				ChannelGraphView newChannel = ChannelGraphView.builder().id(channelId).build();
				log.info("[Service] 신규 채널 노드를 생성합니다. id: {}", channelId);
				return channelNeo4jCommand.save(newChannel);
			});

		PostEntity mongoPost = postMongoQuery.getById(postId);

		PostGraphView post = postNeo4jQuery.findByPostId(postId)
			.orElseGet(() -> {
				PostGraphView newPost = PostGraphView.builder()
					.postId(mongoPost.id())
					.link(mongoPost.link())
					.title(mongoPost.title())
					.content(mongoPost.text())
					.domain(mongoPost.domain())
					.siteName(mongoPost.siteName())
					.cluster(mongoPost.cluster() != null ? mongoPost.cluster().intValue() : 0)
					.createdAt(mongoPost.publishedAt())
					.updatedAt(mongoPost.updatedAt())
					.promotesChannels(new HashSet<>())
					.similarPosts(new HashSet<>())
					.build();
				log.info("[Service] 신규 게시글 노드를 생성합니다. id: {}", postId);
				return postNeo4jCommand.save(newPost);
			});

		boolean alreadyPromoted = post.promotesChannels().stream()
			.anyMatch(p -> p.channel().id().equals(channelId));

		if (!alreadyPromoted) {
			PostGraphView.Promote newPromote = PostGraphView.Promote.builder()
				.id(null)
				.channel(channel)
				.build();

			Set<PostGraphView.Promote> newPromotes = new HashSet<>(post.promotesChannels());
			newPromotes.add(newPromote);

			PostGraphView updatedPost = PostGraphView.builder()
				.postId(post.postId())
				.cluster(post.cluster())
				.link(post.link())
				.content(post.content())
				.title(post.title())
				.domain(post.domain())
				.siteName(post.siteName())
				.createdAt(post.createdAt())
				.updatedAt(post.updatedAt())
				.promotesChannels(newPromotes)
				.similarPosts(post.similarPosts())
				.build();

			postNeo4jCommand.save(updatedPost);
		} else {
			throw new ConflictException(ALREADY_EXIST_PROMOTE_RELATION);
		}
	}
}
