package com.w2m.infraesctructura.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.w2m.infrastructure.entity.Administrator;
import com.w2m.infrastructure.entity.Logable;
/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class AdministratorTest{

	@InjectMocks
	private Administrator administrator;

	@Mock
	private Logable logable;

	@Test
	void testGetIdentifier() {
		String expectedUsername = "Pedro69";
		administrator.setUsername(expectedUsername);

		String actualUsername = administrator.getIdentifier();

		assertEquals(expectedUsername, actualUsername,
				"The user identifier does not match the expected one");
	}

	@Test
	void testUsername() {
		String expectedUsername = "Pedro69";
		administrator.setUsername(expectedUsername);
		String actualUsername = administrator.getUsername();
		assertEquals(expectedUsername, actualUsername, "The username does not match the expected one");
	}

	@Test
	void testName() {
		String expectedName = "Pedro";
		administrator.setName(expectedName);
		String actualName = administrator.getName();
		assertEquals(expectedName, actualName, "The name does not match the expected one");
	}

	@Test
	void testPassword() {
		String expectedPassword = "1234";
		administrator.setPassword(expectedPassword);
		String actualPassword = administrator.getPassword();
		assertEquals(expectedPassword, actualPassword, "The password does not match the expected one");
	}

	@Test
	void testGetSetUsername() {
		String expectedUsername = "Pedro69";
		administrator.setUsername(expectedUsername);
		String actualUsername = administrator.getUsername();
		assertEquals(expectedUsername, actualUsername, "The username does not match the expected one");
	}

	@Test
	void testGetSetName() {
		String expectedName = "Pedro";
		administrator.setUsername(expectedName);
		String actualName = administrator.getUsername();
		assertEquals(expectedName, actualName, "The name does not match the expected one");
	}

	@Test
	void testGetSetPassword() {
		String expectedPassword = "1234";
		administrator.setPassword(expectedPassword);
		String actualPassword = administrator.getPassword();
		assertEquals(expectedPassword, actualPassword, "The password does not match the expected one");
	}

	@Test
	void testBuilder() {
		String expectedUsername = "Pedro69";
		String expectedName = "Pedro";
		String expectedPassword = "1234";

		Administrator administrator = Administrator.builder().username(expectedUsername)
				.name(expectedName).password(expectedPassword).build();

		assertEquals(expectedUsername, administrator.getUsername(),
				"The username does not match the expected one");
		assertEquals(expectedName, administrator.getName(), "The name does not match the expected one");
		assertEquals(expectedPassword, administrator.getPassword(),
				"The password does not match the expected one");
	}
}

