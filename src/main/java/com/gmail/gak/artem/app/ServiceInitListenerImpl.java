package com.gmail.gak.artem.app;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.jsoup.nodes.Element;


@SpringComponent
public class ServiceInitListenerImpl implements VaadinServiceInitListener {
    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.addBootstrapListener(response -> {
            final Element head = response.getDocument().head();

            head.prepend("<meta name=\"theme-color\" content=\"#ffffff\">");
            head.prepend("<meta name=\"msapplication-TileColor\" content=\"#ffc40d\">");
            head.prepend("<meta name=\"msapplication-config\" content=\"browserconfig.xml\" />");

            head.prepend("<link rel=\"icon\" type=\"image/png\" sizes=\"32x32\" href=\"icons/favicon-32x32.png\">");
            head.prepend("<link rel=\"icon\" type=\"image/png\" sizes=\"16x16\" href=\"icons/favicon-16x16.png\">");
            head.prepend("<link rel=\"apple-touch-icon\" sizes=\"180x180\" href=\"icons/apple-touch-icon.png\">");
            head.prepend("<link rel=\"mask-icon\" href=\"icons/safari-pinned-tab.svg\" color=\"#5bbad5\">\n");
            head.prepend("<link rel=\"manifest\" href=\"manifest.json\">");
        });
    }
}
