package org.vetau.domain.service.event.impl;

import org.springframework.stereotype.Service;
import org.vetau.domain.service.event.EventDoService;

@Service
public class EventDoServiceImpl implements EventDoService {
    @Override
    public String getEvent() {
        return "Event from domain";
    }
}
