package com.vigilante.retriever.v1.drug.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.v1.drug.adapter.in.web.DrugApi;
import com.vigilante.retriever.v1.drug.adapter.in.web.dto.response.DrugInfoResponse;
import com.vigilante.retriever.v1.drug.adapter.in.web.mapper.DrugWebMapper;
import com.vigilante.retriever.v1.drug.domain.port.in.GetDrugUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DrugController implements DrugApi {

	private final GetDrugUseCase getDrugUseCase;
	private final DrugWebMapper drugWebMapper;

	@Override
	public ResponseEntity<CommonResponse<DrugInfoResponse>> getById(String id) {
		DrugInfoResponse response = drugWebMapper.toResponse(getDrugUseCase.getById(id));
		return CommonResponse.retrieved(response);
	}

	@Override
	public ResponseEntity<CommonResponse<List<DrugInfoResponse>>> findAll() {
		List<DrugInfoResponse> response = drugWebMapper.toResponseList(getDrugUseCase.findAll());
		return CommonResponse.retrieved(response);
	}

	@Override
	public ResponseEntity<CommonResponse<List<DrugInfoResponse>>> getByArgot(String argot) {
		List<DrugInfoResponse> response = drugWebMapper.toResponseList(getDrugUseCase.getByArgot(argot));
		return CommonResponse.retrieved(response);
	}
}
