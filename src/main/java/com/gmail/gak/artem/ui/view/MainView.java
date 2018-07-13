package com.gmail.gak.artem.ui.view;

import com.gmail.gak.artem.ui.component.MainLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import static com.gmail.gak.artem.app.StaticValue.PATH_DEFAULT;
import static com.gmail.gak.artem.app.StaticValue.VIEWPORT;

@Route(PATH_DEFAULT)
@Viewport(VIEWPORT)
public class MainView extends MainLayout implements RouterLayout {
    public MainView() {
    }
}
