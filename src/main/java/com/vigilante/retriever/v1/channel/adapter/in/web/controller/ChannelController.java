package com.vigilante.retriever.v1.channel.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.v1.channel.adapter.in.web.ChannelApi;
import com.vigilante.retriever.v1.channel.adapter.in.web.dto.response.ChannelInfoResponse;
import com.vigilante.retriever.v1.channel.adapter.in.web.mapper.ChannelWebMapper;
import com.vigilante.retriever.v1.channel.domain.port.in.GetChannelUseCase;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ChannelController implements ChannelApi {

	private final GetChannelUseCase getChannelUseCase;
	private final ChannelWebMapper channelWebMapper;

	@Override
	public ResponseEntity<CommonResponse<List<ChannelInfoResponse>>> findAll() {
		List<ChannelInfoResponse> responses = channelWebMapper.toResponseList(getChannelUseCase.findAll());
		return ResponseEntity.ok(CommonResponse.retrieved(responses));
	}

	@Override
	public ResponseEntity<CommonResponse<ChannelInfoResponse>> findByChannelId(Long ChannelId) {
		ChannelInfoResponse response = channelWebMapper.toResponse(getChannelUseCase.getByChannelId(ChannelId));
		return ResponseEntity.ok(CommonResponse.retrieved(response));
	}

	@Override
	public ResponseEntity<CommonResponse<List<ChannelInfoResponse>>> findByTitleContaining(String title) {
		List<ChannelInfoResponse> responses = channelWebMapper.toResponseList(
			getChannelUseCase.findByTitleContaining(title));
		return ResponseEntity.ok(CommonResponse.retrieved(responses));
	}
}
