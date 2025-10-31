package com.vigilante.retriever.v1.channel.domain.graphview;

import java.util.Set;

import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;
import com.vigilante.retriever.v1.post.domain.graphview.PostGraphView;

import lombok.Builder;

@Builder
public record ChannelGraphView(
	Long channelId,
	String title,
	String username,
	String status,
	Set<ArgotGraphView> sellsArgots,
	Set<PostGraphView> promotedByPosts
) {
}
