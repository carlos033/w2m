package com.w2m.application.adapter;

import java.util.Optional;
import org.springframework.stereotype.Component;
import com.w2m.domain.repository.AdministratorRepository;
import com.w2m.infrastructure.entity.Administrator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AdministratorAdapter{
	private final AdministratorRepository userRepository;

	public Optional<Administrator> findById(String user) {
		return userRepository.findById(user);
	}
}
