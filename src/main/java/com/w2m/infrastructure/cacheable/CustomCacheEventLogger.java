package com.w2m.infrastructure.cacheable;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomCacheEventLogger implements CacheEventListener<Object, Object> {
	private static final String MESSAGE = "Cache event = {}, Key = {},  Old value = {}, New value = {}";

	@Override
	public void onEvent(CacheEvent<?, ?> cacheEvent) {
		log.info(MESSAGE, cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getOldValue(),
				cacheEvent.getNewValue());
	}
}
