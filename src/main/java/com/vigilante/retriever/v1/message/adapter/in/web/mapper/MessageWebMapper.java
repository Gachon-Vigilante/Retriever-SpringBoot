package com.vigilante.retriever.v1.message.adapter.in.web.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.message.adapter.in.web.dto.response.MessageInfoResponse;
import com.vigilante.retriever.v1.message.domain.entity.MessageEntity;

@Component
public class MessageWebMapper {

	public MessageInfoResponse toResponse(MessageEntity entity) {

		MessageEntity.Media media = entity.media();

		MessageInfoResponse.Media mediaResponse = null;

		if (media != null) {
			mediaResponse = MessageInfoResponse.Media.builder()
				.url(media.url())
				.fileType(media.fileType())
				.mineType(media.mineType())
				.fileId(media.fileId())
				.accessHash(media.accessHash())
				.fileSize(media.fileSize())
				.build();
		}

		return MessageInfoResponse.builder()
			.id(entity.id())
			.channelId(entity.channelId())
			.message(entity.message())
			.messageId(entity.messageId())
			.date(entity.date())
			.editDate(entity.editDate())
			.editHide(entity.editHide())
			.entities(entity.entities())
			.forwards(entity.forwards())
			.fromId(entity.fromId())
			.fwdDate(entity.fwdDate())
			.fwdFromId(entity.fwdFromId())
			.fwdFromName(entity.fwdFromName())
			.groupedId(entity.groupedId())
			.legacy(entity.legacy())
			.media(mediaResponse)
			.mediaUnread(entity.mediaUnread())
			.mentioned(entity.mentioned())
			.out(entity.out())
			.post(entity.post())
			.reactions(entity.reactions())
			.replyToMsgId(entity.replyToMsgId())
			.senderType(entity.senderType())
			.silent(entity.silent())
			.updatedAt(entity.updatedAt())
			.viaBotId(entity.viaBotId())
			.views(entity.views())
			.argots(entity.argots())
			.build();
	}

	public List<MessageInfoResponse> toResponseList(List<MessageEntity> entities) {
		return entities.stream()
			.map(this::toResponse)
			.toList();
	}
}
