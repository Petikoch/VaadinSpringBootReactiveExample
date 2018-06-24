package ch.petikoch.examples.vaadinspringbootreactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ReplayProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ChatService {

	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	private final ReplayProcessor<String> chatMessages = ReplayProcessor.create();

	public Flux<String> chatMessages() {
		return chatMessages.serialize();
	}

	public void addChatMessage(String msg) {
		chatMessages.onNext(TIME_FORMATTER.format(LocalDateTime.now()) + " " + msg);
	}

}