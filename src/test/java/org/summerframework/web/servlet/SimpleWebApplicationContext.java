/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.web.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.summerframework.beans.BeansException;
import org.summerframework.beans.MutablePropertyValues;
import org.summerframework.beans.PropertyValue;
import org.summerframework.context.ApplicationContext;
import org.summerframework.context.ApplicationContextException;
import org.summerframework.context.support.StaticMessageSource;
import org.summerframework.ui.context.Theme;
import org.summerframework.ui.context.ThemeSource;
import org.summerframework.ui.context.support.SimpleTheme;
import org.summerframework.ui.context.support.UiApplicationContextUtils;
import org.summerframework.web.context.support.StaticWebApplicationContext;
import org.summerframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.summerframework.web.servlet.mvc.Controller;
import org.summerframework.web.servlet.mvc.SimpleFormController;
import org.summerframework.web.servlet.support.RequestContextUtils;
import org.summerframework.web.servlet.theme.AbstractThemeResolver;

/**
 * @author Juergen Hoeller
 * @since 21.05.2003
 */
public class SimpleWebApplicationContext extends StaticWebApplicationContext {

    public SimpleWebApplicationContext(ApplicationContext parent, String namespace) throws BeansException, ApplicationContextException {
        super(parent, namespace);
    }

    public void setServletContext(ServletContext servletContext) {
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.addPropertyValue(new PropertyValue("commandClass", "com.interface21.beans.TestBean"));
        pvs.addPropertyValue(new PropertyValue("formView", "form"));
        registerSingleton("/form.do", SimpleFormController.class, pvs);

        registerSingleton("/locale.do", LocaleChecker.class, null);

        addMessage("test", Locale.ENGLISH, "test message");
        addMessage("test", Locale.CANADA, "Canadian & test message");

        registerSingleton(UiApplicationContextUtils.THEME_SOURCE_BEAN_NAME, DummyThemeSource.class, null);
        super.setServletContext(servletContext);
    }


    public static class LocaleChecker implements Controller {

        public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if (!(RequestContextUtils.getWebApplicationContext(request) instanceof SimpleWebApplicationContext)) {
                throw new ServletException("Incorrect WebApplicationContext");
            }
            if (!(RequestContextUtils.getLocaleResolver(request) instanceof AcceptHeaderLocaleResolver)) {
                throw new ServletException("Incorrect LocaleResolver");
            }
            if (!Locale.CANADA.equals(RequestContextUtils.getLocale(request))) {
                throw new ServletException("Incorrect Locale");
            }
            return null;
        }
    }

    public static class DummyThemeSource implements ThemeSource {

        private StaticMessageSource messageSource;

        public DummyThemeSource() {
            messageSource = new StaticMessageSource();
            messageSource.addMessage("themetest", Locale.ENGLISH, "theme test message");
        }

        public Theme getTheme(String themeName) {
            if (AbstractThemeResolver.ORIGINAL_DEFAULT_THEME_NAME.equals(themeName)) {
                return new SimpleTheme(AbstractThemeResolver.ORIGINAL_DEFAULT_THEME_NAME, messageSource);
            } else {
                return null;
            }
        }
    }

}
