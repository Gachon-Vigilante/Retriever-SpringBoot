package com.vigilante.retriever.v1.message.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.v1.message.adapter.in.web.MessageApi;
import com.vigilante.retriever.v1.message.adapter.in.web.dto.response.MessageInfoResponse;
import com.vigilante.retriever.v1.message.adapter.in.web.mapper.MessageWebMapper;
import com.vigilante.retriever.v1.message.domain.port.in.GetMessageUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController implements MessageApi {

	private final GetMessageUseCase getMessageUseCase;
	private final MessageWebMapper messageWebMapper;

	@Override
	public ResponseEntity<CommonResponse<List<MessageInfoResponse>>> findAll() {
		List<MessageInfoResponse> responses = messageWebMapper.toResponseList(getMessageUseCase.findAll());
		return ResponseEntity.ok(CommonResponse.retrieved(responses));
	}

	@Override
	public ResponseEntity<CommonResponse<List<MessageInfoResponse>>> findByChannelId(Long channelId) {
		List<MessageInfoResponse> responses = messageWebMapper.toResponseList(
			getMessageUseCase.findByChannelId(channelId));
		return ResponseEntity.ok(CommonResponse.retrieved(responses));
	}
}
