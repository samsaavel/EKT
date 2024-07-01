package com.example.ekt.network

sealed class NetworkResponse<out T : Any> {
    object Failure : NetworkResponse<Nothing>()
    data class Success<out T : Any>(val data: T) : NetworkResponse<T>()
}