package com.example.pokemoncompose.util

/**
 * This is a utility to hold data and map state to that data
 */
data class ApiStateHolder<out T>(val state: State, var data: @UnsafeVariance T?, val msg: String?) {

    companion object {

        fun <T> success(data: T?): ApiStateHolder<T> {
            return ApiStateHolder(State.SUCCESS,data,null)
        }
        fun <T> error(msg: String?): ApiStateHolder<T> {
            return ApiStateHolder(State.ERROR,null,msg)
        }
        fun <T> loading() : ApiStateHolder<T> {
            return ApiStateHolder(State.LOADING,null,null)
        }
    }
}

enum class State {
    SUCCESS,
    ERROR,
    LOADING
}