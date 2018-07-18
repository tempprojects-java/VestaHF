package com.gmail.gak.artem.ui.view.apanel.user;

import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.backend.data.entity.User;
import com.gmail.gak.artem.ui.component.CountButton;
import com.gmail.gak.artem.ui.component.SearchField;
import com.gmail.gak.artem.ui.component.ToolbarLayout;
import com.gmail.gak.artem.ui.view.apanel.AdminPanelView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.List;

import static com.gmail.gak.artem.app.StaticValue.PATH_ADMIN_PANEL;
import static com.gmail.gak.artem.app.StaticValue.PATH_USER;

@Route(value = PATH_USER, layout = AdminPanelView.class)
@RouteAlias(value = PATH_ADMIN_PANEL, layout = AdminPanelView.class)
public class UserView extends VerticalLayout {

    private Grid<User> grid;
    private ComboBox<Role> comboBox;
    private SearchField searchField;
    private Button addBtn;
    private CountButton deleteBtn;

    private UserPresenter presenter;

    public UserView(UserPresenter presenter) {
        this.presenter = presenter;

        addToolbar();
        addGrid();

        presenter.init(this);
    }

    private void addToolbar() {
        comboBox = new ComboBox<>();
        comboBox.setItemLabelGenerator(Role::getName);
        comboBox.setPlaceholder("Все");
        comboBox.addValueChangeListener(event -> presenter.filterChanged(event.getValue()));

        addBtn = new Button("Создать", event -> presenter.create());
        addBtn.getElement().setAttribute("theme", "primary");

        deleteBtn = new CountButton("Удалить", event -> presenter.delete());
        deleteBtn.getElement().setAttribute("theme", "error");

        searchField = new SearchField();
        searchField.setPlaceholder("Найти по имени");
        searchField.addValueChangeListener(event -> presenter.filterChanged(event.getValue()));

        ToolbarLayout toolbarLayout = new ToolbarLayout();
        toolbarLayout.add(ToolbarLayout.SIDE.LEFT, addBtn, deleteBtn);
        toolbarLayout.add(ToolbarLayout.SIDE.RIGHT, comboBox, searchField);

        add(toolbarLayout);
    }

    private void addGrid() {
        grid = new Grid<>();

        GridMultiSelectionModel<User> selectionModel = (GridMultiSelectionModel<User>) grid.setSelectionMode(Grid.SelectionMode.MULTI);
        selectionModel.addMultiSelectionListener(multiSelectionEvent -> deleteBtn.setAmount(multiSelectionEvent.getAllSelectedItems().size()));
//        selectionModel.setSelectAllCheckboxVisibility(GridMultiSelectionModel.SelectAllCheckboxVisibility.VISIBLE);

        grid.addColumn(User::getId)
                .setHeader("ID")
                .setSortable(true).setSortProperty("id")
                .setFlexGrow(0).setWidth("80px");
        grid.addColumn(User::getEmail)
                .setHeader("E-mail")
                .setSortable(true).setSortProperty("email");
        grid.addColumn(User::getUsername)
                .setHeader("Username")
                .setSortable(true).setSortProperty("username");
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
        grid.addColumn(new ComponentRenderer<>(user -> {
            Button button = new Button(new Icon(VaadinIcon.EDIT));
            button.addClickListener(event -> presenter.edit(user.getId()));
            return button;
        })).setHeader("")
            .setFlexGrow(0).setWidth("100px");

        add(grid);
    }

    public Grid<User> getGrid() {
        return grid;
    }

    public ComboBox<Role> getComboBox() {
        return comboBox;
    }

    public CountButton getDeleteBtn() {
        return deleteBtn;
    }
}
