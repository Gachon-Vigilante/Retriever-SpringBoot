package com.vigilante.retriever.v1.post.adapter.in.web.mapper;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vigilante.retriever.v1.channel.adapter.in.web.mapper.ChannelWebMapper;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.request.CreatePromotionRelationRequest;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostGraphInfoResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostInfoResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostPageResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostTraceResponse;
import com.vigilante.retriever.v1.post.domain.dto.command.CreatePromotionRelationCommand;
import com.vigilante.retriever.v1.post.domain.entity.PostEntity;
import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;
import com.vigilante.retriever.v1.post.domain.vo.PostTraceVO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostWebMapper {

	private final ObjectProvider<ChannelWebMapper> channelWebMapperProvider;
	private final ObjectMapper objectMapper;

	public PostInfoResponse toResponse(PostEntity entity) {
		return PostInfoResponse.builder()
			.id(entity.id())
			.link(entity.link())
			.analysis(mapAnalysis(entity.analysis()))
			.analysisJobId(entity.analysisJobId())
			.description(entity.description())
			.discoveredAt(entity.discoveredAt())
			.domain(entity.domain())
			.html(entity.html())
			.publishedAt(entity.publishedAt())
			.text(entity.text())
			.title(entity.title())
			.updatedAt(entity.updatedAt())
			.similarities(mapSimilarities(entity.similarities()))
			.siteName(entity.siteName())
			.cluster(entity.cluster())
			.build();
	}

	private PostInfoResponse.Analysis mapAnalysis(PostEntity.Analysis analysis) {
		if (analysis == null) {
			return null;
		}

		return PostInfoResponse.Analysis.builder()
			.drugsRelated(analysis.drugsRelated())
			.promotions(mapPromotions(analysis.promotions()))
			.build();
	}

	private List<PostInfoResponse.Promotion> mapPromotions(List<PostEntity.Promotion> promotions) {
		if (promotions == null) {
			return null;
		}

		return promotions.stream()
			.map(promotion -> PostInfoResponse.Promotion.builder()
				.content(promotion.content())
				.identifiers(mapIdentifiers(promotion.identifiers()))
				.build())
			.toList();
	}

	private List<PostInfoResponse.Identifier> mapIdentifiers(List<PostEntity.Identifier> identifiers) {
		if (identifiers == null) {
			return null;
		}

		return identifiers.stream()
			.map(identifier -> PostInfoResponse.Identifier.builder()
				.identifier(identifier.identifier())
				.channelId(identifier.channelId())
				.isProcessed(identifier.isProcessed())
				.error(identifier.error())
				.build())
			.toList();
	}

	private List<PostInfoResponse.Similarity> mapSimilarities(List<PostEntity.Similarity> similarities) {
		if (similarities == null) {
			return null;
		}

		return similarities.stream()
			.map(similarity -> PostInfoResponse.Similarity.builder()
				.postId(similarity.postId())
				.similarity(similarity.similarity())
				.build())
			.toList();
	}

	public List<PostInfoResponse> toResponseList(List<PostEntity> entities) {
		return entities.stream()
			.map(this::toResponse)
			.toList();
	}

	public PostPageResponse toPageResponse(Page<PostEntity> postPage) {
		Page<PostInfoResponse> postInfoPage = postPage.map(this::toResponse);

		return PostPageResponse.builder()
			.totalCount(postInfoPage.getTotalElements())
			.posts(postInfoPage.getContent())
			.build();
	}

	public CreatePromotionRelationCommand toCommand(CreatePromotionRelationRequest createPromotionRelationRequest) {
		return CreatePromotionRelationCommand.builder()
			.id(createPromotionRelationRequest.id())
			.postId(createPromotionRelationRequest.postId())
			.build();
	}

	public PostGraphInfoResponse toGraphResponse(PostGraphView graphView) {
		Set<PostGraphView> similarPosts = graphView.similarPosts();
		Set<PostGraphInfoResponse> mappedSimilarPosts = null;

		if (similarPosts != null) {
			mappedSimilarPosts = similarPosts.stream()
				.map(this::toGraphResponse)
				.collect(Collectors.toSet());
		}

		return PostGraphInfoResponse.builder()
			.postId(graphView.postId())
			.title(graphView.title())
			.link(graphView.link())
			.domain(graphView.domain())
			.content(graphView.content())
			.cluster(graphView.cluster())
			.discoveredAt(graphView.discoveredAt())
			.updatedAt(graphView.updatedAt())
			.isDeleted(graphView.isDeleted())
			.promotesChannels(mapPromotesChannels(graphView.promotesChannels()))
			.similarPosts(mappedSimilarPosts)
			.build();
	}

	private Set<PostGraphInfoResponse.Promote> mapPromotesChannels(Set<PostGraphView.Promote> promotesChannels) {
		if (promotesChannels == null) {
			return null;
		}

		ChannelWebMapper channelWebMapper = channelWebMapperProvider.getObject();
		return promotesChannels.stream()
			.map(promote -> PostGraphInfoResponse.Promote.builder()
				.id(promote.id())
				.channel(channelWebMapper.toGraphResponse(promote.channel()))
				.build())
			.collect(Collectors.toSet());
	}

	public StreamingResponseBody toStreamingResponseBody(Stream<PostGraphView> stream) {
		return out -> {
			try (stream) {
				Iterator<PostGraphView> it = stream.iterator();

				while (it.hasNext()) {
					PostGraphView v = it.next();
					PostGraphInfoResponse resp = toGraphResponse(v);
					out.write((objectMapper.writeValueAsString(resp) + "\n").getBytes(StandardCharsets.UTF_8));
					out.flush();
				}
			}
		};
	}

	// depth 제한을 두어 최상위에서 한 단계의 유사 게시글만 매핑하도록 함
	public Set<PostTraceResponse> mapSimilarPosts(Set<PostTraceVO> similarPosts) {
		if (similarPosts == null || similarPosts.isEmpty()) {
			return Collections.emptySet();
		}
		return similarPosts.stream()
			.map(post -> PostTraceResponse.builder()
				.postId(post.postId())
				.title(post.title())
				.link(post.link())
				.domain(post.domain())
				.content(post.content())
				.cluster(post.cluster())
				.discoveredAt(post.discoveredAt())
				.updatedAt(post.updatedAt())
				.isDeleted(post.isDeleted())
				.similarPosts(mapSimilarShallow(post.similarPosts()))
				.build())
			.collect(Collectors.toSet());
	}

	private Set<PostTraceResponse> mapSimilarShallow(Set<PostTraceVO> similarPosts) {
		if (similarPosts == null || similarPosts.isEmpty()) {
			return Collections.emptySet();
		}
		return similarPosts.stream()
			.map(post -> PostTraceResponse.builder()
				.postId(post.postId())
				.title(post.title())
				.link(post.link())
				.domain(post.domain())
				.content(post.content())
				.cluster(post.cluster())
				.discoveredAt(post.discoveredAt())
				.updatedAt(post.updatedAt())
				.isDeleted(post.isDeleted())
				.similarPosts(Collections.emptySet())
				.build())
			.collect(Collectors.toSet());
	}
}
