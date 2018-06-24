package ch.petikoch.examples.vaadinspringbootreactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ChatService {

	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	private final EmitterProcessor<String> chatMessages = EmitterProcessor.create();

	public Flux<String> chatMessages() {
		return chatMessages.serialize();
	}

	public void addChatMessage(String msg) {
		chatMessages.onNext(TIME_FORMATTER.format(LocalDateTime.now()) + " " + msg);
	}

}