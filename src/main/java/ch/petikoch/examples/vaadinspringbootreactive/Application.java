package ch.petikoch.examples.vaadinspringbootreactive;

import ch.petikoch.examples.vaadinspringbootreactive.service.WorldCupTicker;
import ch.petikoch.examples.vaadinspringbootreactive.vaadin.MainLayout;
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
        WorldCupTicker worldCupTickerService;
        @Autowired
        MainLayout mainLayout;

        @Override
        protected void init(VaadinRequest request) {
            setContent(mainLayout);

	        worldCupTickerService.worldCupResults().subscribe(wmResultat -> mainLayout.setTextAreaValue(wmResultat));
        }

    }
}
