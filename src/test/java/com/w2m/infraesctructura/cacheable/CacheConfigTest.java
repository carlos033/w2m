package com.w2m.infraesctructura.cacheable;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.annotation.EnableCaching;

import com.w2m.infrastructure.cacheable.CacheConfig;
/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@ExtendWith(MockitoExtension.class)
class CacheConfigTest{

	@Test
	void cacheConfigShouldBeAnnotatedWithEnableCaching() {
		assertTrue(CacheConfig.class.isAnnotationPresent(EnableCaching.class));
	}
}
