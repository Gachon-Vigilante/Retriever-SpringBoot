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
import com.vigilante.retriever.v1.drug.adapter.in.web.dto.response.DrugInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/drugs")
@Tag(name = "Drug API", description = "마약 관리 API")
public interface DrugApi {

	@GetMapping("id/{id}")
	@Operation(summary = "ID로 마약 정보 조회", description = "마약의 ID를 사용하여 특정 마약 정보를 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = DRUG_GET_BY_ID_200)})
	@ApiErrorExample(
		include = {"401", "500"},
		custom = {
			@ApiErrorExample.ErrorSpec(code = "404", exampleKey = DRUG_GET_BY_ID_404)
		}
	)
	ResponseEntity<CommonResponse<DrugInfoResponse>> getById(@PathVariable String id);

	@GetMapping("/all")
	@Operation(summary = "모든 마약 정보 조회", description = "시스템에 등록된 모든 마약 정보를 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = DRUG_FIND_ALL_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<DrugInfoResponse>>> findAll();

	@GetMapping("/argot/{argot}")
	@Operation(summary = "은어로 마약 정보 조회", description = "특정 은어와 관련된 마약 정보를 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = DRUG_GET_BY_ARGOT_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<DrugInfoResponse>>> getByArgot(@PathVariable String argot);
}
