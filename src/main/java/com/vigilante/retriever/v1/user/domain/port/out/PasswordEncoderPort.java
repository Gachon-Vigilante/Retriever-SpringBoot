package com.vigilante.retriever.v1.user.domain.port.out;

public interface PasswordEncoderPort {

	String encode(String rawPassword);

	boolean matches(String rawPassword, String encodedPassword);
}
