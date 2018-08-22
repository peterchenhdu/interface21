/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.context;

import com.interface21.context.ApplicationContext;
import com.interface21.context.ApplicationContextAware;
import com.interface21.context.ApplicationContextException;
import com.interface21.context.NoSuchMessageException;

import java.util.Locale;

public class ACATest implements ApplicationContextAware {

    private ApplicationContext ac;

    public void setApplicationContext(ApplicationContext ctx) throws ApplicationContextException {
        // check reinitialization
        if (this.ac != null) {
            throw new IllegalStateException("Already initialized");
        }

        // check message source availability
        if (ctx != null) {
            try {
                ctx.getMessage("code1", null, Locale.getDefault());
            } catch (NoSuchMessageException ex) {
                // expected
            }
        }

        this.ac = ctx;
    }

    public ApplicationContext getApplicationContext() {
        return ac;
    }

}
