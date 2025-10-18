package com.vigilante.retriever.v1.bookmark.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.v1.bookmark.adapter.in.web.BookmarkApi;
import com.vigilante.retriever.v1.bookmark.adapter.in.web.dto.response.BookmarkInfoResponse;
import com.vigilante.retriever.v1.bookmark.adapter.in.web.mapper.BookmarkWebMapper;
import com.vigilante.retriever.v1.bookmark.domain.port.in.AddBookmarkUseCase;
import com.vigilante.retriever.v1.bookmark.domain.port.in.DeleteBookmarkUseCase;
import com.vigilante.retriever.v1.bookmark.domain.port.in.GetAllBookmarksUseCase;
import com.vigilante.retriever.v1.bookmark.domain.port.in.GetBookmarkByIdUseCase;
import com.vigilante.retriever.v1.bookmark.domain.port.in.GetBookmarksByUserUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookmarkController implements BookmarkApi {

	private final AddBookmarkUseCase addBookmarkUseCase;
	private final DeleteBookmarkUseCase deleteBookmarkUseCase;
	private final GetBookmarksByUserUseCase getBookmarksByUserUseCase;
	private final GetBookmarkByIdUseCase getBookmarkByIdUseCase;
	private final GetAllBookmarksUseCase getAllBookmarksUseCase;
	private final BookmarkWebMapper bookmarkWebMapper;

	@Override
	public ResponseEntity<CommonResponse<Void>> addBookmark(String userId, String channelId) {
		addBookmarkUseCase.addBookmark(userId, channelId);
		return ResponseEntity.ok(CommonResponse.created());
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> deleteBookmark(String userId, String bookmarkId) {
		deleteBookmarkUseCase.deleteBookmark(userId, bookmarkId);
		return ResponseEntity.ok(CommonResponse.deleted());
	}

	@Override
	public ResponseEntity<CommonResponse<List<BookmarkInfoResponse>>> getMyBookmarks(String userId) {
		List<BookmarkInfoResponse> response = bookmarkWebMapper.toInfoResponseList(
			getBookmarksByUserUseCase.getBookmarksByUserId(userId));
		return ResponseEntity.ok(CommonResponse.retrieved(response));
	}

	@Override
	public ResponseEntity<CommonResponse<BookmarkInfoResponse>> getBookmarkById(String bookmarkId) {
		BookmarkInfoResponse response = bookmarkWebMapper.toInfoResponse(
			getBookmarkByIdUseCase.getBookmarkById(bookmarkId));
		return ResponseEntity.ok(CommonResponse.retrieved(response));
	}

	@Override
	public ResponseEntity<CommonResponse<List<BookmarkInfoResponse>>> getAllBookmarks() {
		List<BookmarkInfoResponse> response = bookmarkWebMapper.toInfoResponseList(
			getAllBookmarksUseCase.getAllBookmarks());
		return ResponseEntity.ok(CommonResponse.retrieved(response));
	}
}
