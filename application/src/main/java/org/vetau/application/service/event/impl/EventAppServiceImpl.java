package org.vetau.application.service.event.impl;

import org.springframework.stereotype.Service;
import org.vetau.application.service.event.EventAppService;
import org.vetau.domain.service.event.EventDoService;

@Service
public class EventAppServiceImpl implements EventAppService {

    public final EventDoService eventDoService;

    public EventAppServiceImpl(EventDoService eventDoService) {
        this.eventDoService = eventDoService;
    }

    @Override
    public String getEvent() {
        return eventDoService.getEvent();
    }
}
