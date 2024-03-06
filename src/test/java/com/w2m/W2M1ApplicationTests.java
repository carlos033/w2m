package com.w2m;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class W2M1ApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true, "The application context should have loaded correctly");
	}

	@Test
	void main() {
		ConfigurableApplicationContext context = SpringApplication.run(W2M1Application.class, new String[] {});
		assertTrue(context.isActive(), "The application context should be active");
	}
}