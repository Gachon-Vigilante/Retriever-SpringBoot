package com.vigilante.retriever.v1.argot.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.v1.argot.adapter.in.web.ArgotGraphApi;
import com.vigilante.retriever.v1.argot.adapter.in.web.dto.response.ArgotGraphInfoResponse;
import com.vigilante.retriever.v1.argot.adapter.in.web.mapper.ArgotWebMapper;
import com.vigilante.retriever.v1.argot.domain.port.in.GetArgotGraphUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ArgotGraphController implements ArgotGraphApi {

	private final GetArgotGraphUseCase getArgotGraphUseCase;
	private final ArgotWebMapper argotWebMapper;

	@Override
	public ResponseEntity<CommonResponse<List<ArgotGraphInfoResponse>>> findAll() {
		List<ArgotGraphInfoResponse> responses = argotWebMapper.toGraphResponseList(getArgotGraphUseCase.findAll());
		return ResponseEntity.ok(CommonResponse.retrieved(responses));
	}

	@Override
	public ResponseEntity<CommonResponse<List<ArgotGraphInfoResponse>>> findAllWithRefersTo() {
		List<ArgotGraphInfoResponse> responses = argotWebMapper.toGraphResponseList(
			getArgotGraphUseCase.findAllWithRefersTo());
		return ResponseEntity.ok(CommonResponse.retrieved(responses));
	}
}
