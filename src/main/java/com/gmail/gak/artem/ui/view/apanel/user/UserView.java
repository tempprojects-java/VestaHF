package com.gmail.gak.artem.ui.view.apanel.user;

import com.gmail.gak.artem.ui.view.apanel.AdminPanelView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import static com.gmail.gak.artem.app.StaticValue.PATH_ADMIN_PANEL;
import static com.gmail.gak.artem.app.StaticValue.PATH_USER;

@Route(value = PATH_USER, layout = AdminPanelView.class)
@RouteAlias(value = PATH_ADMIN_PANEL, layout = AdminPanelView.class)
public class UserView extends VerticalLayout {
    public UserView() {
        this.add(new Label("User panel Layout"));
    }
}
