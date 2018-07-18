package com.gmail.gak.artem.ui.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("toolbar-layout")
@HtmlImport("src/component/toolbar-layout.html")
public class ToolbarLayout extends PolymerTemplate<TemplateModel> implements HasSize, HasComponents {

    public enum SIDE {
        LEFT, RIGHT
    }

    public ToolbarLayout() {}

    public void add(SIDE side, Component... components) {
        for(Component component: components) {
            if(SIDE.LEFT.equals(side)) {
                component.getElement().setAttribute("slot", "left");
            }

            if(SIDE.RIGHT.equals(side)) {
                component.getElement().setAttribute("slot", "right");
            }
        }

        this.add(components);
    }
}
