package org.vetau.infrastructure.service.event.impl;

import org.springframework.stereotype.Service;
import org.vetau.infrastructure.service.event.EventInfraService;

@Service
public class EventInfrasImplService implements EventInfraService {
    @Override
    public String getEvent() {
        return "Event from infrastructure";
    }
}
