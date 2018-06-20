package ch.petikoch.examples.vaadinspringbootreactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class WMTicker {

    public Flux<String> wmResults() {
        return Flux.just(
                "Russland - Saudi-Arabien: 5:0",
                "Portugal - Spain: 3:3",
                "Deutschland - Mexico: 0:1"
        )
                .delayElements(Duration.ofSeconds(2))
                .repeat();
    }

}
