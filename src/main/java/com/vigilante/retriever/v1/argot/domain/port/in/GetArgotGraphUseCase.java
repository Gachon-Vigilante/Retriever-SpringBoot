package com.vigilante.retriever.v1.argot.domain.port.in;

import java.util.List;

import com.vigilante.retriever.v1.argot.domain.graphview.ArgotGraphView;

public interface GetArgotGraphUseCase {

	List<ArgotGraphView> findAll();

	List<ArgotGraphView> findAllWithRefersTo();
}
