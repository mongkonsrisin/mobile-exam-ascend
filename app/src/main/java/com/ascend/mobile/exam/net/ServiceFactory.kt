package com.application.data.net

object ServiceFactory {
    fun <API> create(service: Class<API>): API {
        return RetrofitUtils.setupRetrofit().create(service)
    }
}