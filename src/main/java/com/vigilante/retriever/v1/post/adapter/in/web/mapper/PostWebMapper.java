package com.vigilante.retriever.v1.post.adapter.in.web.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostInfoResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostPageResponse;
import com.vigilante.retriever.v1.post.domain.entity.PostEntity;

@Component
public class PostWebMapper {

	public PostInfoResponse toResponse(PostEntity entity) {
		return PostInfoResponse.builder()
			.id(entity.id())
			.link(entity.link())
			.analysis(PostInfoResponse.Analysis.builder()
				.drugsRelated(entity.analysis().drugsRelated())
				.promotions(mapPromotions(entity.analysis().promotions()))
				.build())
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

	private List<PostInfoResponse.Promotion> mapPromotions(List<PostEntity.Promotion> promotions) {
		return promotions.stream()
			.map(promotion -> PostInfoResponse.Promotion.builder()
				.content(promotion.content())
				.identifiers(mapIdentifiers(promotion.identifiers()))
				.build())
			.toList();
	}

	private List<PostInfoResponse.Identifier> mapIdentifiers(List<PostEntity.Identifier> identifiers) {
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
}
