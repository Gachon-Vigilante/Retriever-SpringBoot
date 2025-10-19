package com.vigilante.retriever.v1.channel.domain.enums;

import static com.vigilante.retriever.v1.channel.domain.code.ChannelErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChannelStatus {
	ACTIVE("active"),
	INACTIVE("inactive"),
	RESTRICTED("restricted"),
	BANNED("banned");

	private final String status;

	public static ChannelStatus fromString(String status) {
		for (ChannelStatus channelStatus : ChannelStatus.values()) {
			if (channelStatus.status.equals(status)) {
				return channelStatus;
			}
		}
		throw new NotFoundException(CHANNEL_STATUS_NOT_FOUND);
	}
}
