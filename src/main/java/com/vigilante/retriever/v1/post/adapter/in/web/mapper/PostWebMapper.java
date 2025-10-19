package com.vigilante.retriever.v1.post.adapter.in.web.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.channel.adapter.in.web.mapper.ChannelWebMapper;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.request.CreatePromotionRelationRequest;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostGraphInfoResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostInfoResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostPageResponse;
import com.vigilante.retriever.v1.post.domain.dto.command.CreatePromotionRelationCommand;
import com.vigilante.retriever.v1.post.domain.entity.PostEntity;
import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostWebMapper {

	private final ChannelWebMapper channelWebMapper;

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
		return PostGraphInfoResponse.builder()
			.postId(graphView.postId())
			.cluster(graphView.cluster())
			.link(graphView.link())
			.content(graphView.content())
			.title(graphView.title())
			.domain(graphView.domain())
			.siteName(graphView.siteName())
			.createdAt(graphView.createdAt())
			.updatedAt(graphView.updatedAt())
			.promotesChannels(mapPromotesChannels(graphView.promotesChannels()))
			.similarPosts(graphView.similarPosts().stream()
				.map(this::toGraphResponse)
				.collect(Collectors.toSet()))
			.build();
	}

	private Set<PostGraphInfoResponse.Promote> mapPromotesChannels(Set<PostGraphView.Promote> promotesChannels) {
		if (promotesChannels == null) {
			return null;
		}

		return promotesChannels.stream()
			.map(promote -> PostGraphInfoResponse.Promote.builder()
				.id(promote.id())
				.channel(channelWebMapper.toGraphResponse(promote.channel()))
				.build())
			.collect(Collectors.toSet());
	}

	public List<PostGraphInfoResponse> toGraphResponseList(List<PostGraphView> graphViews) {
		return graphViews.stream()
			.map(this::toGraphResponse)
			.toList();
	}
}
