package com.example.ekt.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ekt.network.NetworkModule
import com.example.ekt.network.NetworkResponse
import com.example.ekt.repository.EKTRepository
import com.example.ekt.repository.EKTRepositoryContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EKTViewModel : ViewModel() {
    private val ektRepository: EKTRepositoryContract =
        EKTRepository(ektApi = NetworkModule().ektApi)
    private val _ektState: MutableStateFlow<EKTState> = MutableStateFlow(EKTState.None)
    val ektState: StateFlow<EKTState> by lazy { _ektState }

    fun handleIntent(intent: EKTIntent) {
        when (intent) {
            EKTIntent.getProductsIntent -> handleFetchProducts()
        }
    }

    private fun handleFetchProducts() {
        _ektState.value = EKTState.Loading
        viewModelScope.launch {
            _ektState.value =
                when (val response = ektRepository.getProducts()) {
                    NetworkResponse.Failure -> EKTState.Failure
                    is NetworkResponse.Success -> EKTState.Success(response.data)
                }
        }
    }
}