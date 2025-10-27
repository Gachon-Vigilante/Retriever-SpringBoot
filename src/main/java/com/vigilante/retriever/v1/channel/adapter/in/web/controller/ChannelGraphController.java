package com.vigilante.retriever.v1.channel.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.v1.channel.adapter.in.web.ChannelGraphApi;
import com.vigilante.retriever.v1.channel.adapter.in.web.dto.response.ChannelGraphInfoResponse;
import com.vigilante.retriever.v1.channel.adapter.in.web.mapper.ChannelWebMapper;
import com.vigilante.retriever.v1.channel.domain.port.in.GetChannelGraphUseCase;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ChannelGraphController implements ChannelGraphApi {

	private final GetChannelGraphUseCase getChannelGraphUseCase;
	private final ChannelWebMapper channelWebMapper;

	@Override
	public ResponseEntity<CommonResponse<List<ChannelGraphInfoResponse>>> getAllChannels() {
		List<ChannelGraphInfoResponse> responses = channelWebMapper.toGraphResponseList(
			getChannelGraphUseCase.findAll());
		return CommonResponse.retrieved(responses);
	}

	@Override
	public ResponseEntity<CommonResponse<List<ChannelGraphInfoResponse>>> findAllWithSells() {
		List<ChannelGraphInfoResponse> responses = channelWebMapper.toGraphResponseList(
			getChannelGraphUseCase.findAllWithSells());
		return CommonResponse.retrieved(responses);
	}
}
