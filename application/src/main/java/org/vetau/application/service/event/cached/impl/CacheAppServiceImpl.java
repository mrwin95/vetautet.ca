package org.vetau.application.service.event.cached.impl;

import org.springframework.stereotype.Service;
import org.vetau.application.service.event.cached.CacheAppService;

@Service
public class CacheAppServiceImpl implements CacheAppService {
    @Override
    public String getFromCache(String id) {
        return "return from app cache";
    }
}
