package com.example.ekt.network

import com.example.ekt.data.EKTResponse
import retrofit2.http.GET

interface EKTApi {
    @GET("apps/moviles/caso-practico/plp")
    suspend fun getProducts(): EKTResponse
}