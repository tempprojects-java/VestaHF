package com.gmail.gak.artem.ui.view.apanel.user;

import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.backend.service.UserService;
import com.gmail.gak.artem.ui.data.UserFilter;
import com.gmail.gak.artem.ui.dataprovider.RoleDataProvider;
import com.gmail.gak.artem.ui.dataprovider.UserDataProvider;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserPresenter {

    private final UserFormDialog userFormDialog;
    private final UserService userService;
    private final UserDataProvider userDataProvider;
    private final RoleDataProvider roleDataProvider;
    private final UserFilter userFilter = new UserFilter();

    private UserView view;

    public UserPresenter(UserDataProvider userDataProvider, RoleDataProvider roleDataProvider, UserFormDialog userFormDialog, UserService userService) {
        this.userService = userService;
        this.userFormDialog = userFormDialog;
        this.userDataProvider = userDataProvider;
        this.roleDataProvider = roleDataProvider;
    }

    public void init(UserView view) {
        this.view = view;

        userDataProvider.setSortOrder(new QuerySortOrder("id", SortDirection.DESCENDING));

        view.getGrid().setDataProvider(userDataProvider);
        view.getComboBox().setDataProvider(roleDataProvider);
    }

    public void create() {
        userFormDialog.open();
    }

    public void delete() {
        System.out.println("Action: delete");
    }

    public void filterChanged(Role role) {
        userFilter.setRole(role);
        userDataProvider.setFilter(userFilter);
    }

    public void filterChanged(String name) {
        userFilter.setName(name);
        userDataProvider.setFilter(userFilter);
    }

    public void edit(Long id) {
//        UI.getCurrent().navigate(ConstContainer.PAGE_CONTACT + "/" + id);
        userFormDialog.read(userService.find(id));
        userFormDialog.open();
    }

//    public void filterChanged(SearchField.ValueChanged event) {
//        userFilter.setName(event.getSource().getValue());
//        userDataProvider.setFilter(userFilter);
//    }
}
