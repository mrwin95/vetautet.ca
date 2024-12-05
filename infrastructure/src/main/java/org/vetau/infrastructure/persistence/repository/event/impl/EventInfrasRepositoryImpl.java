package org.vetau.infrastructure.persistence.repository.event.impl;

import org.springframework.stereotype.Service;
import org.vetau.domain.repository.EventDoRepository;


@Service
public class EventInfrasRepositoryImpl implements EventDoRepository {

    @Override
    public String getEvent() {
        return "return from infrastructure";
    }
}