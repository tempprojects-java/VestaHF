package com.gmail.gak.artem.ui.view.apanel.user;

import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.ui.component.SearchField;
import com.gmail.gak.artem.ui.data.UserFilter;
import com.gmail.gak.artem.ui.dataprovider.RoleDataProvider;
import com.gmail.gak.artem.ui.dataprovider.UserDataProvider;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserPresenter {

    private final UserDataProvider userDataProvider;
    private final RoleDataProvider roleDataProvider;
    private final UserFilter userFilter = new UserFilter();

    private UserView view;

    public UserPresenter(UserDataProvider userDataProvider, RoleDataProvider roleDataProvider) {
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
        System.out.println("Action: create");
    }

    public void delete() {
        System.out.println("Action: delete");
    }

    public void filterChanged(AbstractField.ComponentValueChangeEvent<ComboBox<Role>,Role> event) {
        userFilter.setRole(event.getValue());
        userDataProvider.setFilter(userFilter);
    }

    public void filterChanged(SearchField.ValueChanged event) {
        userFilter.setName(event.getSource().getValue());
        userDataProvider.setFilter(userFilter);
    }
}
