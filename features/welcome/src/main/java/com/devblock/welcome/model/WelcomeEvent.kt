package com.devblock.welcome.model

sealed class WelcomeEvent {
    data class FilterClick(val isFilter: Boolean)
}