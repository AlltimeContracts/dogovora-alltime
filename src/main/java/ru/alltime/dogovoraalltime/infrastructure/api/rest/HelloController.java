package ru.alltime.dogovoraalltime.infrastructure.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alltime.dogovoraalltime.domain.usecases.GetHelloWorld;

@RestController
public class HelloController {

    private final GetHelloWorld getHelloWorld;

    public HelloController() {
        this.getHelloWorld = new GetHelloWorld(); // в реальном проекте лучше через DI
    }

    @GetMapping("/hello")
    public String hello() {
        return getHelloWorld.execute();
    }
}