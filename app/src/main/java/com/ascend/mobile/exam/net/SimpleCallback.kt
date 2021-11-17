package com.application.data.net

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Retrofit callback which also handle error cases.
 */

abstract class SimpleCallback<T> : Callback<T> {
    final override fun onResponse(call: Call<T>, response: Response<T>) {
        onSuccess(response.body())
        val responseCode = response.code()
        when {
            responseCode in 400..511 -> {
                // TODO : Do something here
            }
        }
    }

    final override fun onFailure(call: Call<T>, t: Throwable) {
        onError(t)
    }

    abstract fun onSuccess(result: T?)

    abstract fun onError(throwable: Throwable)
}