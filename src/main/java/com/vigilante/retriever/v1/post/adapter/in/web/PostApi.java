package com.vigilante.retriever.v1.post.adapter.in.web;

import static com.vigilante.retriever.adapter.web.openapi.constant.ExampleKeyConstant.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiErrorExample;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiSuccessExample;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostInfoResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostPageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/posts")
@Tag(name = "Post API", description = "게시글 관리 API")
public interface PostApi {

	@GetMapping("/all")
	@Operation(summary = "게시글 페이지 조회", description = "페이지와 사이즈로 게시글 목록(페이징)을 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = POST_GET_PAGE_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<PostPageResponse>> getAllPosts(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	);

	@GetMapping("/id/{id}")
	@Operation(summary = "ID로 게시글 조회", description = "특정 ID의 게시글 정보를 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = POST_GET_BY_ID_200)})
	@ApiErrorExample(
		include = {"401", "500"},
		custom = {@ApiErrorExample.ErrorSpec(code = "404", exampleKey = POST_GET_BY_ID_404)}
	)
	ResponseEntity<CommonResponse<PostInfoResponse>> getPostById(@PathVariable String id);

	@GetMapping("/title/{title}")
	@Operation(summary = "제목으로 게시글 검색", description = "제목에 포함되는 키워드로 게시글을 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = POST_FIND_BY_TITLE_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<PostInfoResponse>>> getPostsByTitleContaining(@PathVariable String title);
}
