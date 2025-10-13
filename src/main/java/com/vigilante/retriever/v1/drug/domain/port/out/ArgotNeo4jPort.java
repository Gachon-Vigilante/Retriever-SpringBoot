package com.vigilante.retriever.v1.drug.domain.port.out;

import java.util.List;

import com.vigilante.retriever.v1.drug.domain.graphview.ArgotGraphView;

public interface ArgotNeo4jPort {

	List<ArgotGraphView> findAll();

	List<ArgotGraphView> findAllWithRefersTo();
}
