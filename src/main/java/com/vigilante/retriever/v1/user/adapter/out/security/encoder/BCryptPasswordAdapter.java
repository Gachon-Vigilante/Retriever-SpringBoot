package com.vigilante.retriever.v1.user.adapter.out.security.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.user.domain.port.out.PasswordEncoderPort;

@Component
public class BCryptPasswordAdapter implements PasswordEncoderPort {

	private final BCryptPasswordEncoder encoder;

	public BCryptPasswordAdapter() {
		this.encoder = new BCryptPasswordEncoder();
	}

	@Override
	public String encode(String rawPassword) {
		return encoder.encode(rawPassword);
	}

	@Override
	public boolean matches(String rawPassword, String encodedPassword) {
		return encoder.matches(rawPassword, encodedPassword);
	}
}
