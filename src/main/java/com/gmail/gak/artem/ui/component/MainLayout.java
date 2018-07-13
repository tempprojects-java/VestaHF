package com.gmail.gak.artem.ui.component;

import com.flowingcode.addons.applayout.AppLayout;
import com.flowingcode.addons.applayout.menu.MenuItem;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import static com.gmail.gak.artem.app.StaticValue.*;

public class MainLayout extends VerticalLayout {

    private AppLayout appLayout = new AppLayout(APP_NAME);

    public MainLayout() {
        this.setPadding(false);
        this.setSpacing(false);
        this.setMargin(false);

        setMenuItems();
    }

    private void setMenuItems() {
        appLayout.setMenuItems(
                new MenuItem(TITLE_ADMIN_APANEL, ICON_ADMIN_PANEL, () -> UI.getCurrent().navigate(PATH_ADMIN_PANEL)),
                new MenuItem(TITLE_USER_PROFILE, ICON_USER_PROFILE, () -> UI.getCurrent().navigate(PATH_USER_PROFILE)),
                new MenuItem(TITLE_LEDGER, ICON_LEDGER,
                        new MenuItem("Дом бюджет #1"),
                        new MenuItem("Дом бюджет #2")
                )
        );

        this.add(appLayout);
    }
}
