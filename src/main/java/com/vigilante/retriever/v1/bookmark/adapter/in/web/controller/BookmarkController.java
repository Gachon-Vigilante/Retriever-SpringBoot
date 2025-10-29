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
import com.vigilante.retriever.v1.bookmark.domain.port.in.GetBookmarkUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookmarkController implements BookmarkApi {

	private final AddBookmarkUseCase addBookmarkUseCase;
	private final DeleteBookmarkUseCase deleteBookmarkUseCase;
	private final GetBookmarkUseCase getBookmarkUseCase;
	private final BookmarkWebMapper bookmarkWebMapper;

	@Override
	public ResponseEntity<CommonResponse<Void>> addBookmark(String userId, String channelId) {
		addBookmarkUseCase.addBookmark(userId, channelId);
		return CommonResponse.created();
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> deleteBookmark(String userId, String bookmarkId) {
		deleteBookmarkUseCase.deleteBookmark(userId, bookmarkId);
		return CommonResponse.deleted();
	}

	@Override
	public ResponseEntity<CommonResponse<List<BookmarkInfoResponse>>> findByUserId(String userId) {
		List<BookmarkInfoResponse> response = bookmarkWebMapper.toResponseList(
			getBookmarkUseCase.findByUserId(userId));
		return CommonResponse.retrieved(response);
	}

	@Override
	public ResponseEntity<CommonResponse<BookmarkInfoResponse>> getById(String Id) {
		BookmarkInfoResponse response = bookmarkWebMapper.toResponse(
			getBookmarkUseCase.getById(Id));
		return CommonResponse.retrieved(response);
	}
}
