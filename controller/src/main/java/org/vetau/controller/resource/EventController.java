package org.vetau.controller.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vetau.application.service.event.EventAppService;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventAppService eventAppService;

    public EventController(EventAppService eventAppService){
        this.eventAppService = eventAppService;
    }
    @GetMapping
    public String getEvent(){
        return eventAppService.getEvent();
    }
}
