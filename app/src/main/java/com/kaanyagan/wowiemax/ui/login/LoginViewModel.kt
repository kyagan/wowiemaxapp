package com.kaanyagan.wowiemax.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaanyagan.wowiemax.R
import com.kaanyagan.wowiemax.data.Database
import com.kaanyagan.wowiemax.data.entity.model.User
import com.kaanyagan.wowiemax.data.entity.state.LoginMessageState
import com.kaanyagan.wowiemax.data.entity.state.LoginState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class LoginViewModel:ViewModel() {

    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    val state: StateFlow<LoginState> = _state

    private val _message: MutableSharedFlow<LoginMessageState> = MutableSharedFlow()
    val message: SharedFlow<LoginMessageState> = _message

    fun login(email:String, password:String){
        viewModelScope.launch {
            if(!email.isNullOrEmpty() && !password.isNullOrEmpty()){
                _state.emit(LoginState.Loading)
                delay(1000)
                Database.users.firstOrNull{it.email == email}?.let{user->
                    if(user.password == password){
                        _state.emit(LoginState.Success)
                    }else{
                        _state.emit(LoginState.Error(NullPointerException()))
                        _message.emit(LoginMessageState.Warning(R.string.password_wrong))
                    }
                }?: kotlin.run {
                    _state.emit(LoginState.Error(NullPointerException()))
                    _message.emit(LoginMessageState.Error(R.string.user_not_found))
                }
            }else{
                _message.emit(LoginMessageState.Warning(R.string.fill_in_the_blanks))
            }
        }
    }
    fun signUp(name:String, email:String, password:String, confirm:String){
        viewModelScope.launch {
            if(!email.isNullOrEmpty() && !password.isNullOrEmpty() && !name.isNullOrEmpty() && !confirm.isNullOrEmpty()){
                _state.emit(LoginState.Loading)
                delay(1000)
                Database.users.firstOrNull{it.email == email}?.let{user->
                    _state.emit(LoginState.Error(NullPointerException()))
                    _message.emit(LoginMessageState.Error(R.string.user_already_exists))
                }?: kotlin.run {
                    if (password == confirm) {
                        val user = User(
                            Database.users.lastIndex + 1,
                            "$name",
                            "$name",
                            "$email",
                            "$password"
                        )
                        if (Database.users.add(user)) {
                            _state.emit(LoginState.Success)
                        } else {
                            _state.emit(LoginState.Error(NullPointerException()))
                            _message.emit(LoginMessageState.Error(R.string.user_not_add))
                        }
                    } else {
                        _state.emit(LoginState.Error(NullPointerException()))
                        _message.emit(LoginMessageState.Warning(R.string.confirm_is_not_equal_password))
                    }
                }
            }else{
                _message.emit(LoginMessageState.Warning(R.string.fill_in_the_blanks))
            }
        }
    }
}