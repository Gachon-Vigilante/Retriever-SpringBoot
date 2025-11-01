package com.vigilante.retriever.v1.post.adapter.in.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.vigilante.retriever.adapter.web.dto.response.CommonResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.PostGraphApi;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.request.CreatePromotionRelationRequest;
import com.vigilante.retriever.v1.post.adapter.in.web.dto.response.PostGraphInfoResponse;
import com.vigilante.retriever.v1.post.adapter.in.web.mapper.PostWebMapper;
import com.vigilante.retriever.v1.post.domain.dto.command.CreatePromotionRelationCommand;
import com.vigilante.retriever.v1.post.domain.port.in.GetPostGraphUseCase;
import com.vigilante.retriever.v1.post.domain.port.in.GetPostTraceUseCase;
import com.vigilante.retriever.v1.post.domain.port.in.SavePostGraphRelationUseCase;
import com.vigilante.retriever.v1.post.domain.port.in.SyncPostGraphUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostGraphController implements PostGraphApi {

	private final GetPostGraphUseCase getPostGraphUseCase;
	private final GetPostTraceUseCase getPostTraceUseCase;
	private final SavePostGraphRelationUseCase savePostGraphRelationUseCase;
	private final SyncPostGraphUseCase syncPostGraphUseCase;
	private final PostWebMapper postWebMapper;

	@Override
	public ResponseEntity<StreamingResponseBody> getAllPost() {
		StreamingResponseBody streamBody = postWebMapper.toStreamingResponseBody(getPostGraphUseCase.getAllPost());
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_NDJSON).body(streamBody);
	}

	@Override
	public ResponseEntity<StreamingResponseBody> getPostsByCluster(int cluster) {
		StreamingResponseBody streamBody = postWebMapper.toStreamingResponseBody(getPostGraphUseCase.getPostsByCluster(cluster));
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_NDJSON).body(streamBody);
	}

	@Override
	public ResponseEntity<CommonResponse<PostGraphInfoResponse>> getPostTrace(String postId) {
		PostGraphInfoResponse response = postWebMapper.toGraphResponse(
			getPostTraceUseCase.getPostTrace(postId));
		return CommonResponse.retrieved(response);
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> syncPosts() {
		syncPostGraphUseCase.syncPosts();
		return CommonResponse.success();
	}

	@Override
	public ResponseEntity<CommonResponse<Void>> createPromotionRelation(
		CreatePromotionRelationRequest createPromotionRelationRequest) {
		CreatePromotionRelationCommand command = postWebMapper.toCommand(createPromotionRelationRequest);
		savePostGraphRelationUseCase.createPromotionRelation(command);
		return CommonResponse.created();
	}
}
