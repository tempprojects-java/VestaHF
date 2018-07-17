package com.gmail.gak.artem.ui.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("toolbar-layout")
@HtmlImport("src/component/toolbar-layout.html")
public class ToolbarLayout extends PolymerTemplate<TemplateModel> implements HasSize {

    public enum SIDE {
        LEFT, RIGHT
    }

    @Id("left")
    private Div left;

    @Id("right")
    private Div right;

    public ToolbarLayout() {
        setWidth("100%");
    }

    public void add(SIDE side, Component... components) {
        if(SIDE.LEFT.equals(side)) {
            left.add(components);
        }

        if(SIDE.RIGHT.equals(side)) {
            right.add(components);
        }
    }
}
