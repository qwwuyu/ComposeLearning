package com.qwwuyu.base.gson

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

internal class Exclusion : ExclusionStrategy {
    override fun shouldSkipField(f: FieldAttributes): Boolean {
        return false
    }

    override fun shouldSkipClass(clazz: Class<*>?): Boolean {
        return false
    }
}