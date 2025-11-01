package com.vigilante.retriever.v1.drug.domain.port.out;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.vigilante.retriever.v1.drug.domain.graphview.DrugGraphView;

public interface DrugNeo4jPort {

	List<DrugGraphView> findAll();

	Optional<DrugGraphView> findDrugWithAllRelationships(@Param("drugBankId") String drugBankId);
}
