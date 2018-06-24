package ch.petikoch.examples.vaadinspringbootreactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class WorldCupTickerService {

	public Flux<String> worldCupResults() {
		return Flux.just(
				"Russia - Saudi-Arabia: 5:0",
				"Portugal - Spain: 3:3",
				"Germany - Mexico: 0:1",
				"Switzerland - Serbia: 2:1"
		)
				.delayElements(Duration.ofSeconds(2))
				.repeat();
	}

}
