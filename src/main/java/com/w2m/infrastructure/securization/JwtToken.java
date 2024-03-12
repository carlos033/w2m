package com.w2m.infrastructure.securization;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.w2m.infrastructure.entity.Administrator;
import com.w2m.infrastructure.entity.Logable;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@Component
public class JwtToken{
	private static final String USER_NAME = "userName";
	private static final String JWT_DECODE_EXCEPTION = "Error decoding the token";
	private static final String INVALID_CLAIM_EXCEPTION =
			"The 'user' object is not of the expected type";
	private static final String USER = "user";
	private String secret;

	public JwtToken() {
		this.secret = generateRandomSecret();
	}

	private String generateRandomSecret() {
		byte[] randomBytes = new byte[256];
		new SecureRandom().nextBytes(randomBytes);
		return Base64.getEncoder().encodeToString(randomBytes);
	}

	public String getTokenIdentifier(String token) {
		return getClaimFromToken(token, DecodedJWT::getSubject);
	}

	public Instant getTokenExpiration(String token) {
		return getClaimFromToken(token, DecodedJWT::getExpiresAt).toInstant();
	}

	public <T> T getClaimFromToken(String token, Function<DecodedJWT, T> claimsResolver) {
		return claimsResolver.apply(decodeToken(token));
	}

	private DecodedJWT decodeToken(String token) {
		try {
			return (JWT.require(Algorithm.HMAC512(secret)).build()).verify(token);
		} catch (JWTDecodeException exception) {
			throw new JWTDecodeException(JWT_DECODE_EXCEPTION, exception);
		}
	}

	private Boolean hasTokenExpired(String token) {
		final Instant expiration = getTokenExpiration(token);
		return expiration.isBefore(Instant.now());
	}

	public String generateToken(Logable user) {
		final Map<String, Object> claims = new HashMap<>();
		final Map<String, Object> userMap = new HashMap<>();
		userMap.put(USER, user.getIdentifier());
		userMap.put(USER_NAME, ((Administrator) user).getName());
		claims.put(USER, new HashMap<>(userMap));
		String subject = ((Administrator) user).getIdentifier();
		return doGenerateToken(claims, subject);
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		final Algorithm algorithm = Algorithm.HMAC512(secret);
		final Instant now = Instant.now();
		final Instant expirationTime = now.plus(Duration.ofDays(1));
		Object usuarioClaimsObj = claims.get(USER);
		if (usuarioClaimsObj instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> usuarioClaims = (Map<String, Object>) usuarioClaimsObj;
			return JWT.create().withSubject(subject).withIssuedAt(now).withExpiresAt(expirationTime)
					.withClaim(USER, usuarioClaims).sign(algorithm);
		} else {
			throw new InvalidClaimException(INVALID_CLAIM_EXCEPTION);
		}
	}

	public Boolean validateToken(String token, String expectedSubject) {
		final String identifier = getTokenIdentifier(token);
		return (identifier.equals(expectedSubject) && !hasTokenExpired(token));
	}
}
