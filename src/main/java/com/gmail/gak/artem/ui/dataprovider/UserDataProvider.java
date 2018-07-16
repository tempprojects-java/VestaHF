package com.gmail.gak.artem.ui.dataprovider;

import com.gmail.gak.artem.app.StaticValue;
import com.gmail.gak.artem.backend.data.entity.User;
import com.gmail.gak.artem.backend.service.UserService;
import com.gmail.gak.artem.ui.data.UserFilter;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.QuerySortOrderBuilder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;

import java.util.List;
import java.util.function.Consumer;

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserDataProvider extends FilterablePageableDataProvider<User, UserFilter> {
    private final UserService userService;

    private List<QuerySortOrder> defaultSortOrders;
    private Consumer<Page<User>> pageObserver;

    public UserDataProvider(UserService userService) {
        this.userService = userService;
        setSortOrders(StaticValue.DEFAULT_SORT_DIRECTION, StaticValue.USER_SORT_FIELDS);
    }

    private void setSortOrders(Sort.Direction direction, String[] properties) {
        QuerySortOrderBuilder builder = new QuerySortOrderBuilder();
        for (String property : properties) {
            if (direction.isAscending()) {
                builder.thenAsc(property);
            } else {
                builder.thenDesc(property);
            }
        }
        defaultSortOrders = builder.build();
    }

    @Override
    protected Page<User> fetchFromBackEnd(Query<User, UserFilter> query, Pageable pageable) {
        UserFilter filter = query.getFilter().orElse(null);
        if(filter == null) {
            return userService.find(pageable);
        }

        Page<User> page = userService.findAnyMatching(filter.getName(), filter.getRole(), pageable);
        if (pageObserver != null) {
            pageObserver.accept(page);
        }
        return page;
    }

    @Override
    protected int sizeInBackEnd(Query<User, UserFilter> query) {
        UserFilter filter = query.getFilter().orElse(null);

        if(filter == null){
            return userService.count();
        }

        return userService.countAnyMatching(filter.getName(), filter.getRole());
    }

    @Override
    protected List<QuerySortOrder> getDefaultSortOrders() {
        return defaultSortOrders;
    }
}
