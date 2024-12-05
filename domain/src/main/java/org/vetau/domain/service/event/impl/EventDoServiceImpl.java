package org.vetau.domain.service.event.impl;

import org.springframework.stereotype.Service;
import org.vetau.domain.repository.EventDoRepository;
import org.vetau.domain.service.event.EventDoService;

@Service
public class EventDoServiceImpl implements EventDoService {

    private final EventDoRepository eventDoRepository;
    public EventDoServiceImpl(EventDoRepository eventDoRepository){
        this.eventDoRepository = eventDoRepository;
    }
    @Override
    public String getEvent() {
        return eventDoRepository.getEvent();
    }
}
