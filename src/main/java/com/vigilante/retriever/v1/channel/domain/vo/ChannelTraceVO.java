package com.vigilante.retriever.v1.channel.domain.vo;

import java.util.Set;

import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;
import com.vigilante.retriever.v1.channel.domain.graphview.ChannelGraphView;
import com.vigilante.retriever.v1.post.domain.vo.PostTraceVO;

import lombok.Builder;

@Builder
public record ChannelTraceVO(
	Long channelId,
	String title,
	String username,
	String status,
	Set<PostTraceVO> promotingPosts,
	Set<ArgotGraphView> sellsArgots
) {

	public static ChannelTraceVO create(ChannelGraphView channelGraphView) {
		return ChannelTraceVO.builder()
			.channelId(channelGraphView.channelId())
			.title(channelGraphView.title())
			.username(channelGraphView.username())
			.status(channelGraphView.status())
			.promotingPosts(PostTraceVO.mapPromotingPosts(channelGraphView.promotedByPosts()))
			.sellsArgots(channelGraphView.sellsArgots())
			.build();
	}
}
