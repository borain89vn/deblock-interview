package com.devblock.login.model

internal sealed class LoginEvent {
    data class Login(
        val email: String,
        val password: String
    ): LoginEvent()
}