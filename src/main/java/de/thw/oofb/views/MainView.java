package de.thw.oofb.views;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route
public class MainView extends AbstractLoggedinView {

    private static final long serialVersionUID = 2616027330332669858L;

    public MainView() {
        add(new Label("Hello " + VaadinSession.getCurrent().getAttribute("username")));
    }
}