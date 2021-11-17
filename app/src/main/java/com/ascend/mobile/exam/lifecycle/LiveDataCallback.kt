package com.application.app.lifecycle

import com.application.data.net.SimpleCallback

open class LiveDataCallback<T>(private vararg val liveDataList: ResourceLiveData<T>) :
    SimpleCallback<T>() {
    override fun onSuccess(result: T?) {
        liveDataList.forEach { it.postResponse(result) }
    }

    override fun onError(throwable: Throwable) {
        liveDataList.forEach { it.postError(throwable) }
    }
}