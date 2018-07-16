package com.gmail.gak.artem.ui.view.apanel.user;

import com.gmail.gak.artem.ui.dataprovider.UserDataProvider;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserPresenter {

    private final UserDataProvider userDataProvider;
    private UserView view;

    public UserPresenter(UserDataProvider userDataProvider) {
        this.userDataProvider = userDataProvider;
    }

    public void init(UserView view) {
        this.view = view;

        userDataProvider.setSortOrder(new QuerySortOrder("id", SortDirection.DESCENDING));

        view.getGrid().setDataProvider(userDataProvider);


    }
}
