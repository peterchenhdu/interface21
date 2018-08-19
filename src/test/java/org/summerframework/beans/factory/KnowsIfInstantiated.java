
/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.beans.factory;

public class KnowsIfInstantiated {

    private static boolean instantiated;

    public static void clearInstantiationRecord() {
        instantiated = false;
    }

    public static boolean wasInstantiated() {
        return instantiated;
    }

    /**
     * Constructor for KnowsIfLoaded.
     */
    public KnowsIfInstantiated() {
        instantiated = true;
    }

}
