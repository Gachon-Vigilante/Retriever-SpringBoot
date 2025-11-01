package com.vigilante.retriever.v1.drug.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.v1.drug.adapter.in.web.DrugGraphApi;
import com.vigilante.retriever.v1.drug.adapter.in.web.dto.response.DrugGraphInfoResponse;
import com.vigilante.retriever.v1.drug.adapter.in.web.dto.response.DrugTraceResponse;
import com.vigilante.retriever.v1.drug.adapter.in.web.mapper.DrugWebMapper;
import com.vigilante.retriever.v1.drug.domain.port.in.GetDrugGraphUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DrugGraphController implements DrugGraphApi {

	private final GetDrugGraphUseCase getDrugGraphUseCase;
	private final DrugWebMapper drugWebMapper;

	@Override
	public ResponseEntity<CommonResponse<List<DrugGraphInfoResponse>>> findAll() {
		List<DrugGraphInfoResponse> response = drugWebMapper.toGraphResponseList(getDrugGraphUseCase.findAll());

		return CommonResponse.retrieved(response);
	}

	@Override
	public ResponseEntity<DrugTraceResponse> getDrugTrace(String drugBankId) {
		DrugTraceResponse response = drugWebMapper.toTraceResponse(
			getDrugGraphUseCase.findDrugWithAllRelationships(drugBankId)
		);

		return ResponseEntity.ok(response);
	}
}
