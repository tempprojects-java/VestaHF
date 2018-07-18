package com.gmail.gak.artem.ui.view.apanel.user;

import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.backend.data.entity.User;
import com.gmail.gak.artem.ui.dataprovider.RoleDataProvider;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserFormDialog extends Dialog {

    private TextField username;
    private TextField email;
    private ComboBox<Role> role;

    private Button saveBtn;
    private Button closeBtn;

    private BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);
    private RoleDataProvider roleDataProvider;

    public UserFormDialog(RoleDataProvider roleDataProvider) {
        setCloseOnEsc(true);
        setCloseOnOutsideClick(true);

        this.roleDataProvider = roleDataProvider;

        role = new ComboBox<>("Роль");
        role.setItemLabelGenerator(Role::getName);
        role.setDataProvider(this.roleDataProvider);

        username = new TextField("Username");
        email = new TextField("E-mail");

        binder.forField(username).asRequired("This value can't be empty").bind(User::getUsername, User::setUsername);
        binder.bind(email, "email");

        saveBtn = new Button("Сохранить");
        saveBtn.getElement().setAttribute("theme", "primary");
        closeBtn = new Button("Закрыть", event -> close());

        HorizontalLayout buttonsLayout = new HorizontalLayout(saveBtn, closeBtn);
        buttonsLayout.setAlignItems(FlexComponent.Alignment.END);
        buttonsLayout.getStyle().set("paddingTop", "var(--lumo-space-m)");

        FormLayout formLayout = new FormLayout();
        formLayout.getElement().getStyle().set("max-width", "300px");
        formLayout.add(username, email, role);

        add(formLayout, buttonsLayout);
    }

    public void read(User user) {
        binder.readBean(user);
    }

    public void write(User user) throws ValidationException {
        binder.writeBean(user);
    }

    public Registration addSaveListener(ComponentEventListener<ClickEvent<Button>> listener) {
        return saveBtn.addClickListener(listener);
    }
}
