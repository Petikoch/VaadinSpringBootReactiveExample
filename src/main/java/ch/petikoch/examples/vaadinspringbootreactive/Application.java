package ch.petikoch.examples.vaadinspringbootreactive;

import ch.petikoch.examples.vaadinspringbootreactive.gui.vaadin.VaadinGui;
import ch.petikoch.examples.vaadinspringbootreactive.service.ChatService;
import ch.petikoch.examples.vaadinspringbootreactive.service.WorldCupTickerService;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Push
	@Theme("valo")
	@SpringUI(path = "")
	public static class VaadinUI extends UI {

		@Autowired
		WorldCupTickerService worldCupTickerServiceService;
		@Autowired
		ChatService chatService;

		@Autowired
		VaadinGui vaadinGui;

		@Override
		protected void init(VaadinRequest request) {
			setContent(vaadinGui);

			worldCupTickerServiceService.worldCupResults()
					.subscribe(worldCupResults -> vaadinGui.setWorldCupTickerText(worldCupResults));

			chatService.chatMessages()
					.subscribe(msg -> vaadinGui.addChatMessage(msg));
			vaadinGui.myChatMessages()
					.map(msg -> "[" + getUIId() + "] " + msg)
					.subscribe(msg -> chatService.addChatMessage(msg));
		}

	}
}
