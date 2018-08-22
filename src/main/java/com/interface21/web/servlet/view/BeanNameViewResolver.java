/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.interface21.web.servlet.view;

import java.util.Locale;

import javax.servlet.ServletException;

import org.interface21.web.servlet.View;
import org.interface21.web.servlet.ViewResolver;
import org.interface21.context.support.ApplicationObjectSupport;

/**
 * Simple implementation of ViewResolver that interprets a view name
 * as bean name in the current application context, i.e. in the XML
 * file of the executing DispatcherServlet.
 * <p>
 * <p>This resolver can be handy for small applications, keeping all
 * definitions ranging from controllers to views are in the same place.
 * For normal applications, XmlViewResolver will be the better choice,
 * as it separates the XML bean definitions in a dedicated views file.
 * View beans should virtually never have references to any other
 * application beans - such a separation will make this clear.
 * <p>
 * <p>This ViewResolver does not support internationalization.
 * Consider ResourceBundleViewResolver if you need to apply
 * different view resources per locale.
 *
 * @author Juergen Hoeller
 * @see XmlViewResolver
 * @see ResourceBundleViewResolver
 * @since 18.06.2003
 */
public class BeanNameViewResolver extends ApplicationObjectSupport implements ViewResolver {

    public View resolveViewName(String viewName, Locale locale) throws ServletException {
        Object o = getApplicationContext().getBean(viewName);
        if (!(o instanceof View)) {
            throw new ServletException("Bean with name '" + viewName + "' in application context must be of type View");
        }
        return (View) o;
    }

}
