package com.example.ekt.vm

import com.example.ekt.data.EKTResponse

sealed class EKTState() {
    object Loading : EKTState()
    object None : EKTState()
    object Failure : EKTState()
    data class Success(
        val response: EKTResponse,
    ) : EKTState()
}

sealed class EKTIntent() {
    object getProductsIntent : EKTIntent()
}