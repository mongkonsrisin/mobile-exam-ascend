package com.application.app.lifecycle

class Resource<RESPONSE> private constructor(
    val data: RESPONSE? = null,
    val throwable: Throwable? = null,
    val state: ResourceState = ResourceState.Idle
) {

    constructor(response: RESPONSE?) : this(
        data = response,
        state = ResourceState.Success
    )

    constructor(throwable: Throwable?) : this(
        throwable = throwable,
        state = ResourceState.Error
    )

    companion object {
        fun <RESPONSE> isLoading(): Resource<RESPONSE> {
            return Resource(state = ResourceState.Loading)
        }

        fun <RESPONSE> success(response: RESPONSE?): Resource<RESPONSE> {
            return Resource(response)
        }

        fun <RESPONSE> error(throwable: Throwable?): Resource<RESPONSE> {
            return Resource(throwable)
        }
    }
}