package ch.petikoch.examples.vaadinspringbootreactive.gui.vaadin;

import ch.petikoch.examples.vaadinspringbootreactive.gui.Gui;
import com.vaadin.shared.Registration;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@UIScope
public class VaadinGui extends DesignVaadinGui implements Gui {

	@Override
	public void setWorldCupTickerText(String tickerText) {
		getUI().access(() -> worldCupTickerTextArea.setValue(tickerText));
	}

	@Override
	public Flux<String> myChatMessages() {
		return Flux.create(stringFluxSink -> {
					Button.ClickListener clickListener = event -> {
						final String myChatMsg = myChatMessage.getValue();
						if (StringUtils.isNotBlank(myChatMsg)) {
							stringFluxSink.next(myChatMsg);
							myChatMessage.setValue("");
						}
					};

					final Registration registration = sendChatMessageButton.addClickListener(clickListener);
					stringFluxSink.onDispose(registration::remove);
				}
		);
	}

	@Override
	public void addChatMessage(String msg) {
		getUI().access(() -> chatTextArea.setValue(msg + "\n" + chatTextArea.getValue()));
	}

}
