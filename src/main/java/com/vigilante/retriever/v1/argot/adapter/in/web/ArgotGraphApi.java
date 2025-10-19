package com.vigilante.retriever.v1.argot.adapter.in.web;

import static com.vigilante.retriever.adapter.web.openapi.constant.ExampleKeyConstant.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiErrorExample;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiSuccessExample;
import com.vigilante.retriever.v1.argot.adapter.in.web.dto.response.ArgotGraphInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/neo4j/argots")
@Tag(name = "Argot Graph API", description = "마약 은어 그래프 관리 API")
public interface ArgotGraphApi {

	@GetMapping
	@Operation(summary = "모든 마약 은어 그래프 정보 조회", description = "Neo4j에 저장된 모든 은어 정보를 그래프 형태로 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = ARGOT_FIND_ALL_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<ArgotGraphInfoResponse>>> findAll();

	@GetMapping("/depth")
	@Operation(summary = "마약 은어 그래프 정보(깊이 포함) 조회", description = "참조된 마약 관계(depth 포함)를 포함한 은어 그래프 정보를 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = ARGOT_GRAPH_FIND_ALL_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<ArgotGraphInfoResponse>>> findAllWithRefersTo();
}
