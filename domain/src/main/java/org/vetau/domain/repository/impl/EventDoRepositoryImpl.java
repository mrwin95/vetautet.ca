package org.vetau.domain.repository.impl;

import org.springframework.stereotype.Service;
import org.vetau.domain.repository.EventDoRepository;

@Service
public class EventDoRepositoryImpl implements EventDoRepository {
    @Override
    public String getEvent() {
        return "return from domain repository";
    }
}
