package com.vigilante.retriever.v1.post.adapter.in.web.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.PostApi;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostInfoResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostPageResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.mapper.PostWebMapper;
import com.vigilante.retriever.v1.post.application.service.GetPostService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PostController implements PostApi {

	private final GetPostService getPostService;
	private final PostWebMapper postWebMapper;

	@Override
	public ResponseEntity<CommonResponse<PostPageResponse>> getAllPosts(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		PostPageResponse response = postWebMapper.toPageResponse(getPostService.findAll(pageable));
		return ResponseEntity.ok(CommonResponse.retrieved(response));
	}

	@Override
	public ResponseEntity<CommonResponse<PostInfoResponse>> getPostById(String id) {
		PostInfoResponse response = postWebMapper.toResponse(getPostService.getById(id));
		return ResponseEntity.ok(CommonResponse.retrieved(response));
	}

	@Override
	public ResponseEntity<CommonResponse<List<PostInfoResponse>>> getPostsByTitleContaining(String title) {
		List<PostInfoResponse> responses = postWebMapper.toResponseList(getPostService.findByTitleContaining(title));
		return ResponseEntity.ok(CommonResponse.retrieved(responses));
	}
}
