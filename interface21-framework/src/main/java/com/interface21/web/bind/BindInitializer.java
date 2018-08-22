/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.web.bind;

import com.interface21.validation.DataBinder;
import com.interface21.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

/**
 * Callback that allows for initialization of a binder with
 * custom editors before the binding. Used by BindUtils.
 *
 * @author Jean-Pierre PAWLAK
 * @see BindUtils#bind(ServletRequest, Object, String, BindInitializer)
 * @see BindUtils#bindAndValidate(ServletRequest, Object, String, Validator, BindInitializer)
 * @since 08.05.2003
 */
public interface BindInitializer {

    /**
     * Initialize the given binder instance, e.g. with custom editors.
     * Called by BindUtils#bind. This method allows you to register custom
     * editors for certain fields of your command class. For instance, you will
     * be able to transform Date objects into a String pattern and back, in order
     * to allow your JavaBeans to have Date properties and still be able to
     * set and display them in for instance an HTML interface.
     *
     * @param request current request
     * @param binder  new binder instance
     * @throws ServletException in case of invalid state or arguments
     * @see DataBinder#registerCustomEditor
     * @see BindUtils#bind(ServletRequest, Object, String, BindInitializer)
     */
    public void initBinder(ServletRequest request, ServletRequestDataBinder binder)
            throws ServletException;
}
