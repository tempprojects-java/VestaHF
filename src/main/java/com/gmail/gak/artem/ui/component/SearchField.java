package com.gmail.gak.artem.ui.component;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.HasValueChangeMode;
import com.vaadin.flow.data.value.ValueChangeMode;

public class SearchField extends TextField implements HasValueChangeMode {

    private Icon prefix;
    private Div sufix;

    public SearchField() {
        this.setPrefix();
        this.setSuffix();
        this.setValueChangeMode(ValueChangeMode.EAGER);

        this.addKeyPressListener(event -> sufix.setVisible(true));
    }

    private void setPrefix() {
        sufix = new Div(new Icon(VaadinIcon.CLOSE_SMALL));

        sufix.setVisible(false);
        sufix.getStyle().set("cursor", "default");
        sufix.addClickListener(event -> {
            this.clear();
            this.sufix.setVisible(false);
        });

        this.setSuffixComponent(sufix);
    }

    private void setSuffix() {
        prefix = new Icon(VaadinIcon.SEARCH);

        this.setPrefixComponent(prefix);
    }
}
