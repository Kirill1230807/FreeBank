package com.example.freebank.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freebank.core.domain.error.DataError
import com.example.freebank.core.domain.error.MyResult
import com.example.freebank.core.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    private val _events = Channel<LoginEvent>()
    val events = _events.receiveAsFlow()

    fun onEmailChange(newValue: String) {
        email = newValue
        errorMessage = null
    }

    fun onPasswordChange(newValue: String) {
        password = newValue
        errorMessage = null
    }

    fun login() {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Email and password cannot be empty"
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            val result = authRepository.login(email, password)
            
            isLoading = false
            
            when (result) {
                is MyResult.Success -> {
                    _events.send(LoginEvent.Success)
                }
                is MyResult.Error -> {
                    errorMessage = when (result.error) {
                        DataError.Network.UNAUTHORIZED -> "Invalid email or password"
                        DataError.Network.NO_INTERNET -> "No internet connection"
                        DataError.Network.SERVER_ERROR -> "Server error, try again later"
                        else -> "An unknown error occurred"
                    }
                }
            }
        }
    }

    sealed interface LoginEvent {
        data object Success : LoginEvent
    }
}
