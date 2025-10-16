package com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.converter;

import org.springframework.data.convert.PropertyValueConverter;
import org.springframework.data.mongodb.core.convert.MongoConversionContext;

import com.vigilante.retriever.v1.channel.domain.enums.SenderType;

public class SenderTypeValueConverter
	implements PropertyValueConverter<SenderType, String, MongoConversionContext> {

	@Override
	public String write(SenderType value, MongoConversionContext context) {
		return value.getType();
	}

	@Override
	public SenderType read(String value, MongoConversionContext context) {
		return SenderType.fromString(value);
	}
}
