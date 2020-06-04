package de.thw.oofb;

import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import de.thw.oofb.views.MainView;

/**
 * The main view contains a button and a click listener.
 */
@Route("login")
public class LoginView extends VerticalLayout {

    /**
     *
     */
    private static final long serialVersionUID = -1530240125650420313L;

    public LoginView() {
        LoginForm loginForm = new LoginForm(i18n());
        loginForm.setForgotPasswordButtonVisible(false);        

        loginForm.addLoginListener(e -> {
            boolean isAuthenticated = authenticate(e);
            if (isAuthenticated) {
                navigateToMainPage();
            } else {
                loginForm.setError(true);
            }
        });

        add(loginForm);
        setAlignItems(Alignment.CENTER);
    }

    private void navigateToMainPage() {
        if (getUI().isPresent()) {
            getUI().get().navigate(MainView.class);
        }
    }

    // TODO implement actual authentication
    private boolean authenticate(LoginEvent e) {
        VaadinSession currentSession = VaadinSession.getCurrent();
        if (!"Geheim1!".equals(e.getPassword())) {
            currentSession.setAttribute("loggedIn", false);
            return false;
        }
        
        currentSession.setAttribute("loggedIn", true);
        currentSession.setAttribute("username", e.getUsername());

        return true;
    }

    private LoginI18n i18n() {
        final LoginI18n i18n = LoginI18n.createDefault();
    
        i18n.getForm().setTitle("Login");
        i18n.getForm().setUsername("Benutzername");
        i18n.getForm().setSubmit("Einloggen");
        i18n.getForm().setPassword("Passwort");
        i18n.getErrorMessage().setTitle("Benutzer oder Passwort ungültig");
        i18n.getErrorMessage()
            .setMessage("Stellen Sie sicher, dass sie den richtigen Benutzernamen und das richtige Passwort verwenden.");
        return i18n;
    }
}
