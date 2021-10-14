package com.devblock.splash.model

sealed class SplashState {
    object Idle: SplashState()
    object Loading: SplashState()
}