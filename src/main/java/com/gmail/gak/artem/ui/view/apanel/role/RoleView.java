package com.gmail.gak.artem.ui.view.apanel.role;

import com.gmail.gak.artem.ui.view.apanel.AdminPanelView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import static com.gmail.gak.artem.app.StaticValue.PATH_ROLE;

@Route(value = PATH_ROLE, layout = AdminPanelView.class)
public class RoleView extends VerticalLayout {
    public RoleView() {
        this.add(new Label("RoleView panel Layout"));
    }
}