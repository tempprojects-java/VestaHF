package com.gmail.gak.artem.ui.view.apanel.user;

import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.backend.data.entity.User;
import com.gmail.gak.artem.ui.component.SearchBox;
import com.gmail.gak.artem.ui.component.ToolbarLayout;
import com.gmail.gak.artem.ui.view.apanel.AdminPanelView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.List;

import static com.gmail.gak.artem.app.StaticValue.PATH_ADMIN_PANEL;
import static com.gmail.gak.artem.app.StaticValue.PATH_USER;

@Route(value = PATH_USER, layout = AdminPanelView.class)
@RouteAlias(value = PATH_ADMIN_PANEL, layout = AdminPanelView.class)
public class UserView extends VerticalLayout {

    private Grid<User> grid;
    private SearchBox searchBox;
    private Button addBtn;
    private Button deleteBtn;

    private UserPresenter presenter;

    public UserView(UserPresenter presenter) {
        this.presenter = presenter;

        addToolbar();
        addGrid();

        presenter.init(this);
    }

    private void addToolbar() {
        addBtn = new Button("Create", event -> {});
        addBtn.getElement().setAttribute("theme", "primary");

        deleteBtn = new Button("Delete", event -> {});
        deleteBtn.setEnabled(false);
        deleteBtn.getElement().setAttribute("theme", "error");

        searchBox = new SearchBox();
        searchBox.setPlaceholder("Find Name");

        ToolbarLayout toolbarLayout = new ToolbarLayout();
        toolbarLayout.add(ToolbarLayout.SIDE.LEFT, addBtn, deleteBtn);
        toolbarLayout.add(ToolbarLayout.SIDE.RIGHT, searchBox);

        add(toolbarLayout);
    }

    private void addGrid() {
        grid = new Grid<>();

        GridMultiSelectionModel<User> selectionModel = (GridMultiSelectionModel<User>) grid.setSelectionMode(Grid.SelectionMode.MULTI);
//        selectionModel.addMultiSelectionListener(multiSelectionEvent -> presenter.select(multiSelectionEvent));
//        selectionModel.setSelectAllCheckboxVisibility(GridMultiSelectionModel.SelectAllCheckboxVisibility.VISIBLE);

        grid.addColumn(User::getId)
                .setHeader("ID")
                .setSortable(true).setSortProperty("id")
                .setFlexGrow(0).setWidth("80px");
        grid.addColumn(User::getEmail)
                .setHeader("E-mail")
                .setSortable(true).setSortProperty("name");
        grid.addColumn(User::getUsername)
                .setHeader("Username")
                .setSortable(true).setSortProperty("surname");
        grid.addColumn(user -> {
            if(user.getRoles() == null) {
                return "- no role -";
            }

            StringBuilder result = new StringBuilder();
            List<Role> roles = user.getRoles();
            for (Role role: roles) {
                result.append(role.getName());
                result.append(",");
            }
            return result.substring(0, result.length() - 1);
        }).setHeader("Roles");

        add(grid);
    }

    public Grid<User> getGrid() {
        return grid;
    }
}
