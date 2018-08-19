/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.web.servlet.i18n;

import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.summerframework.beans.propertyeditors.LocaleEditor;
import org.summerframework.web.servlet.HandlerInterceptor;
import org.summerframework.web.servlet.LocaleResolver;
import org.summerframework.web.servlet.support.RequestContextUtils;

/**
 * Interceptor that allows for changing the current locale on every request,
 * via a configurable request parameter.
 *
 * @author Juergen Hoeller
 * @see LocaleResolver
 * @since 20.06.2003
 */
public class LocaleChangeInterceptor implements HandlerInterceptor {

    public static final String DEFAULT_PARAM_NAME = "locale";

    private String paramName = DEFAULT_PARAM_NAME;

    /**
     * Set the name of the parameter that contains a locale specification
     * in a locale change request. Default is "locale".
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        String newLocale = request.getParameter(this.paramName);
        if (newLocale != null) {
            LocaleEditor localeEditor = new LocaleEditor();
            localeEditor.setAsText(newLocale);
            localeResolver.setLocale(request, response, (Locale) localeEditor.getValue());
        }
        // proceed in any case
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    }

}
