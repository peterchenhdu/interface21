/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.interface21.web.servlet.theme;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Implementation of ThemeResolver that simply uses a fixed theme.
 * The fixed name can be defined via the defaultTheme property.
 * <p>
 * <p>Note: Does not support setThemeName, as the theme is fixed.
 *
 * @author Jean-Pierre Pawlak
 * @author Juergen Hoeller
 * @see #setDefaultThemeName
 * @since 17.06.2003
 */
public class FixedThemeResolver extends AbstractThemeResolver {

    public String resolveThemeName(HttpServletRequest request) {
        return getDefaultThemeName();
    }

    public void setThemeName(HttpServletRequest request, HttpServletResponse response, String themeName) {
        throw new IllegalArgumentException("Cannot change theme - use a different theme resolution strategy");
    }

}
