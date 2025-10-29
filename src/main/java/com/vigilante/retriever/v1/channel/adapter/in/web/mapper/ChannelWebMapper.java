package com.vigilante.retriever.v1.channel.adapter.in.web.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.argot.adapter.in.web.dto.response.ArgotGraphInfoResponse;
import com.vigilante.retriever.v1.argot.adapter.in.web.mapper.ArgotWebMapper;
import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;
import com.vigilante.retriever.v1.channel.adapter.in.web.dto.response.ChannelGraphInfoResponse;
import com.vigilante.retriever.v1.channel.adapter.in.web.dto.response.ChannelInfoResponse;
import com.vigilante.retriever.v1.channel.domain.entity.ChannelEntity;
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChannelWebMapper {

	private final ArgotWebMapper argotWebMapper;

	public ChannelInfoResponse toResponse(ChannelEntity entity) {
		ChannelEntity.Catalog catalog = entity.catalog();
		ChannelInfoResponse.Catalog catalogResponse = null;
		if (catalog != null) {
			catalogResponse = ChannelInfoResponse.Catalog.builder()
				.messageIds(catalog.messageIds())
				.summary(catalog.summary())
				.build();
		}

		return ChannelInfoResponse.builder()
			.id(entity.id())
			.title(entity.title())
			.username(entity.username())
			.channelId(entity.channelId())
			.about(entity.about())
			.accessHash(entity.accessHash())
			.broadcast(entity.broadcast())
			.callActive(entity.callActive())
			.callNotEmpty(entity.callNotEmpty())
			.checkedAt(entity.checkedAt())
			.date(entity.date())
			.fake(entity.fake())
			.gigaGroup(entity.gigaGroup())
			.hasGeo(entity.hasGeo())
			.hasLink(entity.hasLink())
			.lastMessageDate(entity.lastMessageDate())
			.left(entity.left())
			.megaGroup(entity.megaGroup())
			.min(entity.min())
			.monitoring(entity.monitoring())
			.noForwards(entity.noForwards())
			.participantsCount(entity.participantsCount())
			.photo(entity.photo())
			.restricted(entity.restricted())
			.restrictionReason(mapRestrictionReasons(entity.restrictionReason()))
			.scam(entity.scam())
			.signatures(entity.signatures())
			.slowModeEnabled(entity.slowModeEnabled())
			.status(entity.status())
			.updatedAt(entity.updatedAt())
			.verified(entity.verified())
			.catalog(catalogResponse)
			.build();
	}

	private List<ChannelInfoResponse.RestrictionReason> mapRestrictionReasons(
		List<ChannelEntity.RestrictionReason> reasons) {
		if (reasons == null) {
			return null;
		}

		return reasons.stream()
			.map(reason -> ChannelInfoResponse.RestrictionReason.builder()
				.platform(reason.platform())
				.reason(reason.reason())
				.text(reason.text())
				.build())
			.toList();
	}

	public List<ChannelInfoResponse> toResponseList(List<ChannelEntity> entities) {
		return entities.stream()
			.map(this::toResponse)
			.toList();
	}

	public ChannelGraphInfoResponse toGraphResponse(ChannelGraphView graphView) {
		return ChannelGraphInfoResponse.builder()
			.channelId(graphView.channelId())
			.title(graphView.title())
			.username(graphView.username())
			.status(graphView.status())
			.promotedCount(graphView.promotedCount())
			.sellsArgots(mapSellsArgots(graphView.sellsArgots()))
			.build();
	}

	private Set<ArgotGraphInfoResponse> mapSellsArgots(Set<ArgotGraphView> argotGraphViews) {
		return argotGraphViews.stream()
			.map(argotWebMapper::toGraphResponse)
			.collect(Collectors.toSet());
	}

	public List<ChannelGraphInfoResponse> toGraphResponseList(List<ChannelGraphView> graphViews) {
		return graphViews.stream()
			.map(this::toGraphResponse)
			.toList();
	}
}
