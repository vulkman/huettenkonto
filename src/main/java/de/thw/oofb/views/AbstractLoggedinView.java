package de.thw.oofb.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.server.VaadinSession;

import de.thw.oofb.LoginView;

public abstract class AbstractLoggedinView extends VerticalLayout implements BeforeEnterObserver {

    private static final long serialVersionUID = -903090356469903851L;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        VaadinSession session = VaadinSession.getCurrent();
        if (session.getAttribute("loggedIn") == null || !(boolean) session.getAttribute("loggedIn")) {
            event.rerouteTo(LoginView.class);
        }
    }

}