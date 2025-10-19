package com.vigilante.retriever.adapter.web.openapi.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExampleKeyConstant {

	// Common
	public static final String COMMON_BAD_REQUEST_400 = "Common__Bad__Request__400";
	public static final String COMMON_CONFLICT_409 = "Common__Conflict__409";
	public static final String COMMON_FORBIDDEN_403 = "Common__Forbidden__403";
	public static final String COMMON_NOT_FOUND_404 = "Common__Not__Found__404";
	public static final String COMMON_SERVER_ERROR_500 = "Common__Server__Error__500";
	public static final String COMMON_UNAUTHORIZED_401 = "Common__Unauthorized__401";

	// User
	public static final String USER_CREATE_201 = "User__Create__201";
	public static final String USER_CREATE_409 = "User__Create__409";
	public static final String USER_LOGIN_200 = "User__Login__200";
	public static final String USER_LOGIN_401 = "User__Login__401";
	public static final String USER_LOGIN_404 = "User__Login__404";
	public static final String USER_LOGOUT_200 = "User__Logout__200";
	public static final String USER_REISSUE_200 = "User__Reissue__200";
	public static final String USER_REISSUE_401 = "User__Reissue__401";
	public static final String USER_WITHDRAW_200 = "User__Withdraw__200";
	public static final String USER_GRANT_ROLE_200 = "User__Grant__Role__200";
	public static final String USER_GRANT_ROLE_403 = "User__Grant__Role__403";
	public static final String USER_GRANT_ROLE_404 = "User__Grant__Role__404";
	public static final String USER_FIND_ALL_200 = "User__Find__All__200";

	// Bookmark
	public static final String BOOKMARK_ADD_BOOKMARK_201 = "Bookmark__Add__Bookmark__201";
	public static final String BOOKMARK_ADD_BOOKMARK_409 = "Bookmark__Add__Bookmark__409";
	public static final String BOOKMARK_DELETE_BOOKMARK_200 = "Bookmark__Delete__Bookmark__200";
	public static final String BOOKMARK_DELETE_BOOKMARK_403 = "Bookmark__Delete__Bookmark__403";
	public static final String BOOKMARK_DELETE_BOOKMARK_404 = "Bookmark__Delete__Bookmark__404";
	public static final String BOOKMARK_GET_BY_USER_ID_200 = "Bookmark__Get__By__User__Id__200";
	public static final String BOOKMARK_GET_BY_ID_200 = "Bookmark__Get__By__Id__200";
	public static final String BOOKMARK_GET_BY_ID_404 = "Bookmark__Get__By__Id__404";
	public static final String BOOKMARK_FIND_ALL_200 = "Bookmark__Find__All__200";

	// Drug
	public static final String DRUG_GET_BY_ID_200 = "Drug__Get__By__Id__200";
	public static final String DRUG_FIND_ALL_200 = "Drug__Find__All__200";
	public static final String DRUG_GET_BY_ARGOT_200 = "Drug__Get__By__Argot__200";
	public static final String DRUG_GET_BY_ID_404 = "Drug__Get__By__Id__404";
	public static final String DRUG_GRAPH_FIND_ALL_200 = "Drug__Graph__Find__All__200";

	// Argot
	public static final String ARGOT_FIND_ALL_200 = "Argot__Find__All__200";
	public static final String ARGOT_GRAPH_FIND_ALL_200 = "Argot__Graph__Find__All__200";

	// Channel
	public static final String CHANNEL_FIND_ALL_200 = "Channel__Find__All__200";
	public static final String CHANNEL_GET_BY_ID_200 = "Channel__Get__By__Id__200";
	public static final String CHANNEL_GET_BY_ID_404 = "Channel__Get__By__Id__404";
	public static final String CHANNEL_FIND_BY_TITLE_200 = "Channel__Find__By__Title__200";
	public static final String CHANNEL_GRAPH_FIND_ALL_200 = "Channel__Graph__Find__All__200";
	public static final String CHANNEL_STATUS_404 = "Channel__Status__404";

	// Message
	public static final String MESSAGE_FIND_ALL_200 = "Message__Find__All__200";
	public static final String MESSAGE_FIND_BY_CHANNEL_200 = "Message__Find__By__Channel__200";

	// Post
	public static final String POST_GET_PAGE_200 = "Post__Get__Page__200";
	public static final String POST_GET_BY_ID_200 = "Post__Get__By__Id__200";
	public static final String POST_GET_BY_ID_404 = "Post__Get__By__Id__404";
	public static final String POST_FIND_BY_TITLE_200 = "Post__Find__By__Title__200";
}
