package com.gmail.gak.artem.ui.view.apanel;

import com.gmail.gak.artem.ui.component.NavigationBar;
import com.gmail.gak.artem.ui.data.PageData;
import com.gmail.gak.artem.ui.view.MainView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.gak.artem.app.StaticValue.*;

@ParentLayout(MainView.class)
public class AdminPanelView extends VerticalLayout implements RouterLayout {
    private NavigationBar navigationBar;

    public AdminPanelView() {
        this.setPadding(false);
        this.setSpacing(false);
        this.setMargin(false);

        navigationBar = new NavigationBar();
        List<PageData> pages = new ArrayList<>();

        pages.add(new PageData(PATH_USER, ICON_USER, TITLE_USER));
        pages.add(new PageData(PATH_ROLE, ICON_ROLE, TITLE_ROLE));

        navigationBar.init(pages, PATH_ADMIN_PANEL);

        this.add(navigationBar);
    }
}
