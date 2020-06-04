package de.thw.oofb.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.server.VaadinSession;

import de.thw.oofb.LoginView;

public abstract class AbstractLoggedinView extends AppLayout implements BeforeEnterObserver {

    private static final long serialVersionUID = -903090356469903851L;

    public AbstractLoggedinView() {
        Button logoutButton = new Button("Logout", VaadinIcon.EXIT.create());
        logoutButton.addClickListener((e -> {
            VaadinSession.getCurrent().setAttribute("loggedIn", false);
            if (getUI().isPresent()) {
                getUI().get().navigate(LoginView.class);
            }
        }));

        addToNavbar(logoutButton);

        setContent(initView());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        VaadinSession session = VaadinSession.getCurrent();
        if (session.getAttribute("loggedIn") == null || !(boolean) session.getAttribute("loggedIn")) {
            event.rerouteTo(LoginView.class);
        }
    }

    protected abstract Component initView();

}