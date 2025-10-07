package com.vigilante.retriever.v1.drug.domain.port.out;

import java.util.List;

import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;

public interface DrugNeo4jPort {

	List<DrugGraphView> findAll();
}
