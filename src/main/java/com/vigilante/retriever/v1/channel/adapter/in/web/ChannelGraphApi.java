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
import com.vigilante.retriever.v1.channel.adapter.in.web.dto.response.ChannelGraphInfoResponse;
import com.vigilante.retriever.v1.channel.adapter.in.web.dto.response.ChannelTraceResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/neo4j/channels")
@Tag(name = "Channel Graph API", description = "채널 그래프 관리 API")
public interface ChannelGraphApi {

	@GetMapping
	@Operation(summary = "모든 채널 그래프 정보 조회", description = "Neo4j에 저장된 모든 채널 정보를 그래프 형태로 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = CHANNEL_GRAPH_FIND_ALL_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<ChannelGraphInfoResponse>>> getAllChannels();

	@GetMapping("/depth")
	@Operation(summary = "채널 그래프 정보(깊이 포함) 조회", description = "관련된 판매 관계(depth 포함)를 포함한 채널 그래프 정보를 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = CHANNEL_GRAPH_FIND_ALL_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<ChannelGraphInfoResponse>>> findAllWithSells();

	@GetMapping("{channelId}")
	@Operation(summary = "채널 기준 데이터 조회", description = "특정 채널을 기준으로 관련된 채널 및 판매 데이터를 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = CHANNEL_GET_TRACE_200)})
	@ApiErrorExample(
		include = {"401", "500"},
		custom = {@ApiErrorExample.ErrorSpec(code = "404", exampleKey = CHANNEL_GET_TRACE_404)}
	)
	ResponseEntity<CommonResponse<ChannelTraceResponse>> getChannelTrace(@PathVariable Long channelId);
}
