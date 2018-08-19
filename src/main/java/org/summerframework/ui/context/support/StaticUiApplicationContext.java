/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.ui.context.support;

import org.summerframework.beans.BeansException;
import org.summerframework.context.ApplicationContext;
import org.summerframework.context.ApplicationContextException;
import org.summerframework.context.support.StaticApplicationContext;
import org.summerframework.ui.context.Theme;
import org.summerframework.ui.context.ThemeSource;

/**
 * Adds theme capabilities for UI contexts.
 *
 * @author Jean-Pierre Pawlak
 */
public class StaticUiApplicationContext extends StaticApplicationContext implements ThemeSource {

    private ThemeSource themeSource;

    /**
     * Standard constructor.
     */
    public StaticUiApplicationContext() throws BeansException, ApplicationContextException {
        super();
    }

    /**
     * Constructor with parent context.
     */
    public StaticUiApplicationContext(ApplicationContext parent) throws BeansException, ApplicationContextException {
        super(parent);
    }

    /**
     * Initialize the theme capability.
     */
    protected void onRefresh() {
        this.themeSource = UiApplicationContextUtils.initThemeSource(this);
    }

    public Theme getTheme(String themeName) {
        return this.themeSource.getTheme(themeName);
    }

}
