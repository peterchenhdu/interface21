/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.web.servlet.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.summerframework.web.servlet.LocaleResolver;
import org.summerframework.web.util.WebUtils;
import org.summerframework.web.servlet.support.RequestContext;

/**
 * Implementation of LocaleResolver that uses a locale attribute in the user's
 * session in case of a custom setting, with a fallback to the accept header locale.
 * This is most appropriate if the application needs user sessions anyway.
 * <p>
 * <p>Custom controllers can override the user's locale by calling setLocale,
 * e.g. responding to a locale change request.
 *
 * @author Juergen Hoeller
 * @since 27.02.2003
 */
public class SessionLocaleResolver implements LocaleResolver {

    /**
     * Name of the session attribute that holds the locale. Only used
     * internally by this implementation. Use RequestContext.getLocale()
     * to retrieve the current locale in controllers or views.
     *
     * @see RequestContext#getLocale
     */
    public static final String LOCALE_SESSION_ATTRIBUTE_NAME = SessionLocaleResolver.class.getName() + ".LOCALE";

    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = (Locale) WebUtils.getSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME);
        // specific locale, or fallback to request locale?
        return (locale != null ? locale : request.getLocale());
    }

    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        WebUtils.setSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME, locale);
    }

}
