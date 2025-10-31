package com.vigilante.retriever.v1.channel.domain.port.in;

import com.vigilante.retriever.v1.channel.domain.vo.ChannelTraceVO;

public interface GetChannelTraceUseCase {

	ChannelTraceVO getChannelPostTrace(Long channelId);
}
