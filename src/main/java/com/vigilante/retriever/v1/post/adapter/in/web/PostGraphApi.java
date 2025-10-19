package com.vigilante.retriever.v1.post.adapter.in.web;

import static com.vigilante.retriever.adapter.web.openapi.constant.ExampleKeyConstant.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiErrorExample;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiSuccessExample;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.request.CreatePromotionRelationRequest;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostGraphInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/neo4j/posts")
@Tag(name = "Post Graph API", description = "게시글 그래프 관리 API")
public interface PostGraphApi {

	@GetMapping("/streamed")
	@Operation(summary = "모든 게시글 그래프 조회(스트리밍)", description = "Neo4j에 저장된 모든 게시글 그래프 정보를 스트리밍 형식으로 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = POST_GRAPH_GET_ALL_STREAMED_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<PostGraphInfoResponse>>> getAllPost();

	@GetMapping("/sync")
	@Operation(summary = "게시글 그래프 동기화", description = "외부 소스에서 게시글을 동기화하여 그래프를 업데이트합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = POST_GRAPH_SYNC_200)})
	@ApiErrorExample(
		include = {"401", "500"},
		custom = {@ApiErrorExample.ErrorSpec(code = "400", exampleKey = POST_GRAPH_SYNC_400)}
	)
	ResponseEntity<CommonResponse<Void>> syncPosts();

	@PostMapping("/relation")
	@Operation(summary = "홍보 관계 생성", description = "게시글과 채널 간 홍보 관계를 생성합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "201", exampleKey = POST_GRAPH_CREATE_RELATION_201)})
	@ApiErrorExample(
		include = {"401", "500"},
		custom = {@ApiErrorExample.ErrorSpec(code = "409", exampleKey = POST_GRAPH_CREATE_RELATION_409)}
	)
	ResponseEntity<CommonResponse<Void>> createPromotionRelation(@RequestBody CreatePromotionRelationRequest request);
}
