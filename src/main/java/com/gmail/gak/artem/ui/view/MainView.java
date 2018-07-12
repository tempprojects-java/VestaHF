package com.gmail.gak.artem.ui.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import static com.gmail.gak.artem.app.StaticValue.VIEWPORT;

@Route("")
@PageTitle("HomeAccount")
@Viewport(VIEWPORT)
public class MainView extends VerticalLayout implements RouterLayout {
    public MainView() {
        add(new Label("Hello World!"));
    }
}
