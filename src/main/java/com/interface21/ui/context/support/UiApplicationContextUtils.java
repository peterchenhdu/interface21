/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.interface21.ui.context.support;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.interface21.beans.factory.NoSuchBeanDefinitionException;
import org.interface21.ui.context.ThemeSource;
import org.interface21.context.ApplicationContext;
import org.interface21.ui.context.NestingThemeSource;

/**
 * Utilities common to all UI application context implementations.
 *
 * @author Jean-Pierre Pawlak
 * @author Juergen Hoeller
 * @since 17.06.2003
 */
public abstract class UiApplicationContextUtils {

    /**
     * Name of the ThemeSource bean in the factory.
     * If none is supplied, theme resolution is delegated to the parent.
     *
     * @see ThemeSource
     */
    public static final String THEME_SOURCE_BEAN_NAME = "themeSource";

    private static final Log logger = LogFactory.getLog(UiApplicationContextUtils.class);

    /**
     * Initialize the theme source for the given application context.
     *
     * @param applicationContext current application context
     * @return the initialized theme source (will never be null)
     */
    public static ThemeSource initThemeSource(ApplicationContext applicationContext) {
        ThemeSource themeSource;
        try {
            themeSource = (ThemeSource) applicationContext.getBean(THEME_SOURCE_BEAN_NAME);
            // set parent theme source if applicable,
            // and if the theme source is defined in this context, not in a parent
            if (applicationContext.getParent() instanceof ThemeSource && themeSource instanceof NestingThemeSource &&
                    Arrays.asList(applicationContext.getBeanDefinitionNames()).contains(THEME_SOURCE_BEAN_NAME)) {
                ((NestingThemeSource) themeSource).setParent((ThemeSource) applicationContext.getParent());
            }
        } catch (NoSuchBeanDefinitionException ex) {
            logger.info("No ThemeSource found for [" + applicationContext.getDisplayName() + "]: using ResourceBundleThemeSource");
            themeSource = new ResourceBundleThemeSource();
        }
        return themeSource;
    }

}
