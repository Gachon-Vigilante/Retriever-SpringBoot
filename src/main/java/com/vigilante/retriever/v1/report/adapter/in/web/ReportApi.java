package com.vigilante.retriever.v1.report.adapter.in.web;

import static com.vigilante.retriever.adapter.web.openapi.constant.ExampleKeyConstant.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiErrorExample;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiSuccessExample;
import com.vigilante.retriever.v1.report.adapter.in.web.dto.ReportInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/report")
@Tag(name = "Report API", description = "보고서 관리 API")
public interface ReportApi {

	@GetMapping("/all")
	@Operation(summary = "모든 신고 조회", description = "시스템에 등록된 모든 신고(리포트) 목록을 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = REPORT_FIND_ALL_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<ReportInfoResponse>>> findAll();

	@GetMapping("/id")
	@Operation(summary = "ID로 신고 조회", description = "주어진 ID로 특정 신고(리포트) 정보를 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = REPORT_GET_BY_ID_200)})
	@ApiErrorExample(
		include = {"401", "500"},
		custom = {@ApiErrorExample.ErrorSpec(code = "404", exampleKey = REPORT_GET_BY_ID_404)}
	)
	ResponseEntity<CommonResponse<ReportInfoResponse>> getById(@RequestParam String id);

	@GetMapping("/channelId")
	@Operation(summary = "채널별 신고 조회", description = "지정된 채널 ID에 대한 신고 목록을 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = REPORT_FIND_BY_CHANNEL_ID_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<ReportInfoResponse>>> getByChannelId(@RequestParam Long channelId);
}
