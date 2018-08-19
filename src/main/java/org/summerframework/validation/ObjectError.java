/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.validation;

import org.summerframework.context.MessageSourceResolvable;
import org.summerframework.context.support.MessageSourceResolvableImpl;

/**
 * Encapsulates an object error, i.e. a global reason for rejection.
 * <p>
 * <p>Normally, an ObjectError has a single code for message resolution.
 *
 * @author Juergen Hoeller
 * @see FieldError
 * @since 10.03.2003
 */
public class ObjectError extends MessageSourceResolvableImpl {

    private final String objectName;

    /**
     * Create a new ObjectError instance, using multiple codes.
     * <p>This is only meant to be used by subclasses like FieldError.
     *
     * @see MessageSourceResolvable#getCodes
     */
    protected ObjectError(String objectName, String[] codes, Object[] args, String defaultMessage) {
        super(codes, args, defaultMessage);
        this.objectName = objectName;
    }

    /**
     * Create a new ObjectError instance, using a default code.
     */
    public ObjectError(String objectName, String code, Object[] args, String defaultMessage) {
        this(objectName, new String[]{code}, args, defaultMessage);
    }

    public String getObjectName() {
        return objectName;
    }

    public String toString() {
        return "Error occurred in object [" + this.objectName + "]: " + resolvableToString();
    }

}
