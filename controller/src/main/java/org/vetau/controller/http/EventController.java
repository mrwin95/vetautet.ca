package org.vetau.controller.http;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.vetau.application.service.event.EventAppService;
import org.vetau.controller.config.ControllerConfig;

import java.util.Random;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventAppService eventAppService;

    private final ControllerConfig config;

    public EventController(EventAppService eventAppService,
                           ControllerConfig config){
        this.eventAppService = eventAppService;
        this.config = config;
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

    @GetMapping("/circuit/breaker")
    @CircuitBreaker(name = "checkRandom", fallbackMethod = "fallBackCircuitBreaker")
    public String circuitBreaker(){

        Random random = new Random();
        int productId = random.nextInt(20) + 1;
//        Integer productId = Math.f Math.random() + 1;
        String url = "https://fakestoreapi.com/products/" + productId;

        return config.restTemplate().getForObject(url, String.class);
    }

    public String fallBackCircuitBreaker(Throwable throwable){
        return "Service error";// throwable.getMessage();
    }
}
