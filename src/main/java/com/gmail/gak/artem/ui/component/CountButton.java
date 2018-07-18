package com.gmail.gak.artem.ui.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;

public class CountButton extends Button {

    private String text;

    public CountButton() {
        setDisabled(true);
    }

    public CountButton(String text) {
        this();
        setText(text);
    }

    public CountButton(String text, ComponentEventListener<ClickEvent<Button>> clickListener) {
        this(text);
        addClickListener(clickListener);
    }

    public CountButton(String text, int count) {
        this();
        setText(text, count);
    }

    public CountButton(String text, int count, ComponentEventListener<ClickEvent<Button>> clickListener) {
        this(text, count);
        addClickListener(clickListener);
    }

    public void reset() {
        setText(text);
        setDisabled(true);
    }

    public void setAmount(int amount) {
        setText(amount);
    }

    public void setText(String text) {
        this.text = text;
        super.setText(text);
    }

    private void setText(int amount) {
        if(amount == 0) {
            reset();
            return;
        }
        super.setText(String.format(text + " (%d)", amount));
        this.setDisabled(false);
    }

    private void setText(String text, int amount) {
        this.text = text;
        super.setText(String.format(text + " (%в)", amount));
    }
}
