package com.vigilante.retriever.v1.channel.adapter.in.web;

import static com.vigilante.retriever.adapter.web.openapi.constant.ExampleKeyConstant.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiErrorExample;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiSuccessExample;
import com.vigilante.retriever.v1.channel.adapter.in.web.dto.response.ChannelInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/channel")
@Tag(name = "Channel API", description = "채널 관리 API")
public interface ChannelApi {

	@GetMapping("/all")
	@Operation(summary = "모든 채널 정보 조회", description = "시스템에 등록된 모든 채널 정보를 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = CHANNEL_FIND_ALL_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<ChannelInfoResponse>>> findAll();

	@GetMapping("/id/{ChannelId}")
	@Operation(summary = "채널 ID로 채널 정보 조회", description = "채널 ID를 사용해 채널 정보를 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = CHANNEL_GET_BY_ID_200)})
	@ApiErrorExample(
		include = {"401", "500"},
		custom = {@ApiErrorExample.ErrorSpec(code = "404", exampleKey = CHANNEL_GET_BY_ID_404)}
	)
	ResponseEntity<CommonResponse<ChannelInfoResponse>> findByChannelId(@PathVariable Long ChannelId);

	@GetMapping("/title/{title}")
	@Operation(summary = "채널 제목 키워드로 채널 검색", description = "제목에 포함되는 키워드로 채널을 검색합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = CHANNEL_FIND_BY_TITLE_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<ChannelInfoResponse>>> findByTitleContaining(@PathVariable String title);
}
