package com.kaanyagan.wowiemax.data.entity.state

sealed class LoginMessageState{
    object Idle:LoginMessageState()
    class Warning(val message:Int):LoginMessageState()
    class Error(val message:Int):LoginMessageState()
}
