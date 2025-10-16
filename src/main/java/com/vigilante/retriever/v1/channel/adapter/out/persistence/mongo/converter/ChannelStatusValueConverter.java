package com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.converter;

import org.springframework.data.convert.PropertyValueConverter;
import org.springframework.data.mongodb.core.convert.MongoConversionContext;

import com.vigilante.retriever.v1.channel.domain.enums.ChannelStatus;

public class ChannelStatusValueConverter
	implements PropertyValueConverter<ChannelStatus, String, MongoConversionContext> {

	@Override
	public String write(ChannelStatus value, MongoConversionContext context) {
		return value.getStatus();
	}

	@Override
	public ChannelStatus read(String value, MongoConversionContext context) {
		return ChannelStatus.fromString(value);
	}
}
