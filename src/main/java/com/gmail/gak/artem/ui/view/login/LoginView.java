package com.gmail.gak.artem.ui.view.login;

import com.vaadin.flow.component.KeyDownEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.gmail.gak.artem.app.StaticValue.PATH_LOGIN;
import static com.gmail.gak.artem.app.StaticValue.VIEWPORT;


@Route(value = PATH_LOGIN)
@PageTitle("HomeAccount")
@Viewport(VIEWPORT)
public class LoginView extends VerticalLayout {

    private AuthenticationProvider authenticationProvider;

    public LoginView(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;

        TextField email = new TextField("E-mail");
        email.setValue("admin@mail.com");
        email.setPlaceholder("example@mail.com");

        PasswordField password = new PasswordField("Password");
        password.setValue("qwerty");
        password.setPlaceholder("password");

        Button sendBtn = new Button("Sign In");
        sendBtn.addClickListener(event -> {
            try {
                final Authentication auth = new UsernamePasswordAuthenticationToken(email.getValue(), password.getValue());
                final Authentication authenticated = authenticationProvider.authenticate(auth);
                SecurityContextHolder.getContext().setAuthentication(authenticated);
                UI.getCurrent().navigate("");
            } catch (AuthenticationException ex) {
                Notification.show("Incorrect user or password:" + ex.getMessage());
            }
        });

        addListener(KeyDownEvent.class, event -> {
           if(event.getKey().matches("Enter")) {
               sendBtn.click();
           }
        });

        FormLayout form = new FormLayout(email, password, sendBtn);
        form.setWidth("300px");

        setAlignItems(Alignment.CENTER);
        add(form);
    }

//    @Override
//    public void beforeEnter(BeforeEnterEvent event) {
//        System.out.println("Test");
//        Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
//        List<GrantedAuthority> roles = new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
//
//        if(authenticated != null && !authenticated.getAuthorities().equals(roles)) {
//            UI.getCurrent().getPage().executeJavaScript("window.location = \"http://localhost:8080\"");
//        }
//    }
}
