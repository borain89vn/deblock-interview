package com.devblock.login.model

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success: LoginState()
    data class Fail(val errorMessage: String): LoginState()
}