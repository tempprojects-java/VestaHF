package com.gmail.gak.artem.ui.dataprovider;

import com.gmail.gak.artem.app.StaticValue;
import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.backend.service.RoleService;
import com.gmail.gak.artem.ui.data.RoleFilter;
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

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RoleDataProvider extends FilterablePageableDataProvider<Role, RoleFilter> {
    private final RoleService RoleService;

    private List<QuerySortOrder> defaultSortOrders;

    public RoleDataProvider(RoleService RoleService) {
        this.RoleService = RoleService;
        setSortOrders(StaticValue.DEFAULT_SORT_DIRECTION, StaticValue.ROLE_SORT_FIELDS);
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
    protected List<QuerySortOrder> getDefaultSortOrders() {
        return defaultSortOrders;
    }

    @Override
    protected Page<Role> fetchFromBackEnd(Query<Role, RoleFilter> query, Pageable pageable) {
        RoleFilter filter = query.getFilter().orElse(null);
        if(filter == null) {
            return RoleService.find(pageable);
        }

        Page<Role> page = RoleService.findAnyMatching(filter.getName(), pageable);
        return page;
    }

    @Override
    protected int sizeInBackEnd(Query<Role, RoleFilter> query) {
        RoleFilter filter = query.getFilter().orElse(null);

        if(filter == null){
            return RoleService.count();
        }

        return RoleService.countAnyMatching(filter.getName());
    }
}
