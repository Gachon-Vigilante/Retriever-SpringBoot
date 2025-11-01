package com.vigilante.retriever.v1.drug.adapter.in.web;

import static com.vigilante.retriever.adapter.web.openapi.constant.ExampleKeyConstant.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiErrorExample;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiSuccessExample;
import com.vigilante.retriever.v1.drug.adapter.in.web.dto.response.DrugGraphInfoResponse;
import com.vigilante.retriever.v1.drug.adapter.in.web.dto.response.DrugTraceResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/neo4j/drugs")
@Tag(name = "Drug Graph API", description = "마약 그래프 관리 API")
public interface DrugGraphApi {

	@GetMapping
	@Operation(summary = "모든 마약 그래프 정보 조회", description = "Neo4j에 저장된 모든 마약 정보를 그래프 형태로 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = DRUG_GRAPH_FIND_ALL_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<DrugGraphInfoResponse>>> findAll();

	@GetMapping("/{drugBankId}")
	@Operation(summary = "마약 그래프 상세 추적 조회", description = "지정한 마약에 대해 참조하는 은어와 판매 채널 정보를 포함한 추적 정보를 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = DRUG_GET_TRACE_200)})
	@ApiErrorExample(
		include = {"401", "500"},
		custom = @ApiErrorExample.ErrorSpec(code = "404", exampleKey = DRUG_GET_TRACE_404)
	)
	ResponseEntity<DrugTraceResponse> getDrugTrace(@PathVariable String drugBankId);
}
