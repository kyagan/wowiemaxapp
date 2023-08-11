package com.kaanyagan.wowiemax.data.entity.state

sealed class LoginState{
    object Idle:LoginState()
    object Loading:LoginState()
    object Success:LoginState()
    class Error(exception: Exception):LoginState()
}