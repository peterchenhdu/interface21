/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.ui.context.support;

import org.summerframework.context.ApplicationContext;
import org.summerframework.context.support.AbstractXmlApplicationContext;
import org.summerframework.ui.context.Theme;
import org.summerframework.ui.context.ThemeSource;

/**
 * Adds theme capabilities for UI contexts.
 *
 * @author Jean-Pierre Pawlak
 */
public abstract class AbstractXmlUiApplicationContext
        extends AbstractXmlApplicationContext implements ThemeSource {

    private ThemeSource themeSource;

    /**
     * Standard constructor.
     */
    public AbstractXmlUiApplicationContext() {
        super();
    }

    /**
     * Constructor with parent context.
     */
    public AbstractXmlUiApplicationContext(ApplicationContext parent) {
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
