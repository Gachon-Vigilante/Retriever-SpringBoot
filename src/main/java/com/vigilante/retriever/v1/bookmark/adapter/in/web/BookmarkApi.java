package com.vigilante.retriever.v1.bookmark.adapter.in.web;

import static com.vigilante.retriever.adapter.web.openapi.constant.ExampleKeyConstant.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiErrorExample;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiSuccessExample;
import com.vigilante.retriever.v1.bookmark.adapter.in.web.dto.response.BookmarkInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/bookmarks")
@Tag(name = "Bookmark API", description = "북마크 관리 API")
public interface BookmarkApi {

	@PostMapping("/{channelId}")
	@Operation(summary = "북마크 추가", description = "특정 채널을 현재 사용자의 북마크에 추가합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "201", exampleKey = BOOKMARK_ADD_BOOKMARK_201)})
	@ApiErrorExample(
		include = {"401", "500"},
		custom = {
			@ApiErrorExample.ErrorSpec(code = "409", exampleKey = BOOKMARK_ADD_BOOKMARK_409)
		}
	)
	ResponseEntity<CommonResponse<Void>> addBookmark(
		@AuthenticationPrincipal String userId,
		@PathVariable String channelId);

	@DeleteMapping("/{bookmarkId}")
	@Operation(summary = "북마크 삭제", description = "특정 북마크를 삭제합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = BOOKMARK_DELETE_BOOKMARK_200)})
	@ApiErrorExample(
		include = {"401", "500"},
		custom = {
			@ApiErrorExample.ErrorSpec(code = "403", exampleKey = BOOKMARK_DELETE_BOOKMARK_403),
			@ApiErrorExample.ErrorSpec(code = "404", exampleKey = BOOKMARK_DELETE_BOOKMARK_404)
		}
	)
	ResponseEntity<CommonResponse<Void>> deleteBookmark(
		@AuthenticationPrincipal String userId,
		@PathVariable String bookmarkId);

	@GetMapping("/me")
	@Operation(summary = "내 북마크 목록 조회", description = "현재 사용자의 모든 북마크 목록을 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = BOOKMARK_GET_MY_BOOKMARKS_200)})
	@ApiErrorExample(include = {"401", "500"})
	ResponseEntity<CommonResponse<List<BookmarkInfoResponse>>> getMyBookmarks(
		@AuthenticationPrincipal String userId);

	@GetMapping("/{bookmarkId}")
	@Operation(summary = "북마크 단건 조회", description = "특정 ID의 북마크 정보를 조회합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = BOOKMARK_GET_BOOKMARK_BY_ID_200)})
	@ApiErrorExample(
		include = {"401", "500"},
		custom = {
			@ApiErrorExample.ErrorSpec(code = "404", exampleKey = BOOKMARK_GET_BOOKMARK_BY_ID_404)
		}
	)
	ResponseEntity<CommonResponse<BookmarkInfoResponse>> getBookmarkById(
		@PathVariable String bookmarkId);

	@GetMapping("/all")
	@Operation(summary = "모든 북마크 목록 조회 (관리자용)", description = "시스템의 모든 북마크 목록을 조회합니다. 관리자 권한이 필요합니다.")
	@ApiSuccessExample({@ApiSuccessExample.Success(code = "200", exampleKey = BOOKMARK_GET_ALL_BOOKMARKS_200)})
	@ApiErrorExample(include = {"401", "403", "500"})
	ResponseEntity<CommonResponse<List<BookmarkInfoResponse>>> getAllBookmarks();
}
