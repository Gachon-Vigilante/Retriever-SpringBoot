package com.vigilante.retriever.global.common.mapper;

import java.util.List;

public interface GenericNeo4jMapper<N, G> {

	// Node를 GraphView로 변환
	N toNode(G graphView);

	// Node를 GraphView로 변환
	G toGraphView(N node);

	// GraphView 목록을 Node 목록으로 변환
	List<N> getNodeList(List<G> graphViewList);

	// Node 목록을 GraphView 목록으로 변환
	List<G> getGraphViewList(List<N> nodeList);
}
