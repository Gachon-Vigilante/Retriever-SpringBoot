package com.vigilante.retriever.v1.report.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.v1.report.adapter.in.web.ReportApi;
import com.vigilante.retriever.v1.report.adapter.in.web.dto.ReportInfoResponse;
import com.vigilante.retriever.v1.report.adapter.in.web.mapper.ReportWebMapper;
import com.vigilante.retriever.v1.report.domain.port.in.GetReportUseCase;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ReportController implements ReportApi {

	private final GetReportUseCase getReportUseCase;
	private final ReportWebMapper reportWebMapper;

	@Override
	public ResponseEntity<CommonResponse<List<ReportInfoResponse>>> findAll() {
		List<ReportInfoResponse> responses = reportWebMapper.toResponseList(getReportUseCase.findAll());
		return ResponseEntity.ok(CommonResponse.retrieved(responses));
	}

	@Override
	public ResponseEntity<CommonResponse<ReportInfoResponse>> getById(String id) {
		ReportInfoResponse response = reportWebMapper.toResponse(getReportUseCase.getById(id));
		return ResponseEntity.ok(CommonResponse.retrieved(response));
	}

	@Override
	public ResponseEntity<CommonResponse<List<ReportInfoResponse>>> getByChannelId(Long channelId) {
		List<ReportInfoResponse> responses = reportWebMapper.toResponseList(getReportUseCase.getByChannelId(channelId));
		return ResponseEntity.ok(CommonResponse.retrieved(responses));
	}
}
