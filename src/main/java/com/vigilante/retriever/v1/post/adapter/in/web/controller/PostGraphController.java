package com.vigilante.retriever.v1.post.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.PostGraphApi;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.request.CreatePromotionRelationRequest;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostGraphInfoResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.mapper.PostWebMapper;
import com.vigilante.retriever.v1.post.domain.dto.command.CreatePromotionRelationCommand;
import com.vigilante.retriever.v1.post.domain.port.in.GetPostGraphUseCase;
import com.vigilante.retriever.v1.post.domain.port.in.SavePostGraphRelationUseCase;
import com.vigilante.retriever.v1.post.domain.port.in.SyncPostGraphUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostGraphController implements PostGraphApi {

	private final GetPostGraphUseCase getPostGraphUseCase;
	private final SavePostGraphRelationUseCase savePostGraphRelationUseCase;
	private final SyncPostGraphUseCase syncPostGraphUseCase;
	private final PostWebMapper postWebMapper;

	@Override
	public ResponseEntity<CommonResponse<List<PostGraphInfoResponse>>> getAllPost() {
		List<PostGraphInfoResponse> responses = postWebMapper.toGraphResponseList(getPostGraphUseCase.getAllPost());
		return ResponseEntity.ok(CommonResponse.retrieved(responses));
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> syncPosts() {
		syncPostGraphUseCase.syncPosts();
		return ResponseEntity.ok(CommonResponse.success());
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> createPromotionRelation(
		CreatePromotionRelationRequest createPromotionRelationRequest) {
		CreatePromotionRelationCommand command = postWebMapper.toCommand(createPromotionRelationRequest);
		savePostGraphRelationUseCase.createPromotionRelation(command);
		return ResponseEntity.ok(CommonResponse.created());
	}
}
