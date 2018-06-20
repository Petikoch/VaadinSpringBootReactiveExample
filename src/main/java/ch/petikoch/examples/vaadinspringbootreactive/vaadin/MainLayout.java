package ch.petikoch.examples.vaadinspringbootreactive.vaadin;

import com.vaadin.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class MainLayout extends DesignMainLayout {

    public void setTextAreaValue(String s){
        getUI().access(() -> textArea.setValue(s));
    }

}
