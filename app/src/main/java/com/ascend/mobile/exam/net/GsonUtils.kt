package com.application.data.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonUtils {

    fun build(): Gson {
        return createBuilder().create()
    }

    private fun createBuilder(): GsonBuilder {
        return GsonBuilder()

    }


}