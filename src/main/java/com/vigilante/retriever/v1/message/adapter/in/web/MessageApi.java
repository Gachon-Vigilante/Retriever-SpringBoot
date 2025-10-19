package com.vigilante.retriever.v1.message.adapter.in.web;

import static com.vigilante.retriever.adapter.web.openapi.constant.ExampleKeyConstant.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiErrorExample;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiSuccessExample;
import com.vigilante.retriever.v1.message.adapter.in.web.dto.response.MessageInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/message")
@Tag(name = "Message API", description = "메시지 관리 API")
public interface MessageApi {

	@GetMapping("/all")
	@Operation(summary = "모든 메시지 조회", description = "시스템에 등록된 모든 메시지를 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = MESSAGE_FIND_ALL_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<MessageInfoResponse>>> findAll();

	@GetMapping("/channel/{channelId}")
	@Operation(summary = "채널별 메시지 조회", description = "주어진 채널ID에 속한 메시지들을 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = MESSAGE_FIND_BY_CHANNEL_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<MessageInfoResponse>>> findByChannelId(@PathVariable Long channelId);
}
