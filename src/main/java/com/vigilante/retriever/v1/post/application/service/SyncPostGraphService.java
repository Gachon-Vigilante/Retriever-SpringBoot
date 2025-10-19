package com.vigilante.retriever.v1.post.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.post.application.command.PostNeo4jCommand;
import com.vigilante.retriever.v1.post.application.query.PostMongoQuery;
import com.vigilante.retriever.v1.post.domain.entity.PostEntity;
import com.vigilante.retriever.v1.post.domain.port.in.SyncPostGraphUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncPostGraphService implements SyncPostGraphUseCase {

	private final PostMongoQuery postMongoQuery;
	private final PostNeo4jCommand postNeo4jCommand;

	@Override
	public void syncPosts() {
		List<PostEntity> mongoPosts = postMongoQuery.findAll();
		int successCount = 0;

		for (PostEntity mongoPost : mongoPosts) {
			String content = mongoPost.text();
			String link = mongoPost.link();
			String postId = mongoPost.id();

			if (content != null && link != null && postId != null) {
				try {
					int updated = postNeo4jCommand.updatePostIdByContentAndLink(content, link, postId);

					if (updated > 0) {
						log.info("✅ 성공: postId={} | link={}", postId, link);

						successCount++;
					} else {
						log.info("⚠️ 대상 없음 (혹은 이미 있음): postId={} | link={}", postId, link);
					}
				} catch (Exception e) {
					log.error("❌ 실패: postId={} | link={}", postId, link);
					e.printStackTrace();
				}
			}
		}

		log.info("✅ postId 업데이트 완료: {}건", successCount);
	}
}
