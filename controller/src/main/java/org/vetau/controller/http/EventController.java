package org.vetau.controller.http;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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

    @GetMapping("/hi")
    @RateLimiter(name = "backendA", fallbackMethod = "fallBackHello")
    public String hello(){
        return "say hi";
    }

    public String fallBackHello(Throwable throwable){
        return "Too many requests";
    }

    @GetMapping("/ho")
    @RateLimiter(name = "backendB", fallbackMethod = "")
    public String sayHi(){
        return "say Ho";
    }
}
