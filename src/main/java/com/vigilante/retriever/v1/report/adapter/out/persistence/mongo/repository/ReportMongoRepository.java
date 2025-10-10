package com.vigilante.retriever.v1.report.adapter.out.persistence.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.report.adapter.out.persistence.mongo.document.ReportDocument;

@Repository
public interface ReportMongoRepository extends MongoRepository<ReportDocument, String> {

	// 채널 아이디로 조회
	List<ReportDocument> findByChannelId(long channelId);
}
