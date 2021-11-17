package com.application.app.lifecycle

import androidx.lifecycle.MutableLiveData

class ResourceLiveData<RESPONSE> : MutableLiveData<Resource<RESPONSE>>() {
    fun postIsLoading() {
        postValue(Resource.isLoading())
    }

    fun postResponse(response: RESPONSE?) {
        postValue(Resource(response))
    }

    fun postError(throwable: Throwable?) {
        postValue(Resource(throwable))
    }
}