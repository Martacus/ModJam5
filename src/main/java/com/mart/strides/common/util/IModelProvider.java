package com.mart.strides.common.util;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;

public interface IModelProvider {

    default void gatherVariants(Int2ObjectArrayMap<String> variants){
        variants.put(0, "inventory");
    }

}
