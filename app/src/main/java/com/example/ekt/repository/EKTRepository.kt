package com.example.ekt.repository

import android.util.Log
import com.example.ekt.data.EKTResponse
import com.example.ekt.network.EKTApi
import com.example.ekt.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface EKTRepositoryContract {
    suspend fun getProducts(): NetworkResponse<EKTResponse>
}

class EKTRepository(
    val ektApi: EKTApi,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : EKTRepositoryContract {

    override suspend fun getProducts(): NetworkResponse<EKTResponse> =
        withContext(dispatcher) {
            try {
                Log.d("********Repository", "getProducts successful")
                val response = ektApi.getProducts()
                NetworkResponse.Success(data = response)
            } catch (e: Throwable) {
                Log.d("********Repository", "getProducts failed")
                NetworkResponse.Failure
            }
        }

}