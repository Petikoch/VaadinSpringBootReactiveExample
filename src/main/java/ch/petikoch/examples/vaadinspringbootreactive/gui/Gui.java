package ch.petikoch.examples.vaadinspringbootreactive.gui;

import reactor.core.publisher.Flux;

public interface Gui {

	void setWorldCupTickerText(String s);

	Flux<String> myChatMessages();

	void addChatMessage(String msg);

}