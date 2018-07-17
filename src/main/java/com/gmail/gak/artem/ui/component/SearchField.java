package com.gmail.gak.artem.ui.component;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.dom.DebouncePhase;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@Tag("search-field")
@HtmlImport("src/component/search-field.html")
@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SearchField extends PolymerTemplate<TemplateModel> implements HasSize {


    @Id("close-btn")
    private Div closeBtn;

    @Id("text-field")
    private TextField textField;

    public SearchField() {
        closeBtn.setVisible(false);

        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.addInputListener(event -> valueChaged());
        ComponentUtil.addListener(textField, FieldValueChanged.class, e -> fireEvent(new ValueChanged(this, false)));
    }

    public void addValueChangeListener(ComponentEventListener<ValueChanged> listener) {
        this.addListener(ValueChanged.class, listener);
    }

    public String getValue() {
        return textField.getValue();
    }

    public void setPlaceholder(String placeholder) {
        textField.setPlaceholder(placeholder);
    }

    @EventHandler
    public void clean() {
        textField.clear();
        closeBtn.setVisible(false);
    }

    private void valueChaged() {
        closeBtn.setVisible(true);
    }

    @DomEvent(value = "value-changed", debounce = @DebounceSettings(timeout = 300, phases = DebouncePhase.TRAILING))
    public static class FieldValueChanged extends ComponentEvent<TextField> {
        public FieldValueChanged(TextField source, boolean fromClient) {
            super(source, fromClient);
        }
    }

    public class ValueChanged extends ComponentEvent<SearchField> {
        public ValueChanged(SearchField source, boolean fromClient) {
            super(source, fromClient);
        }
    }
}
