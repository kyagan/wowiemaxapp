package com.kaanyagan.wowiemax.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kaanyagan.wowiemax.R
import com.kaanyagan.wowiemax.data.Database
import com.kaanyagan.wowiemax.data.entity.state.LoginMessageState
import com.kaanyagan.wowiemax.data.entity.state.LoginState
import com.kaanyagan.wowiemax.databinding.ActivityLoginBinding
import com.kaanyagan.wowiemax.ui.main.MainActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel:LoginViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    companion object{
        const val LOGIN_REMEMBER_ME = "LOGIN_REMEMBER_ME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(LOGIN_REMEMBER_ME,Context.MODE_PRIVATE)
        if(sharedPreferences.getBoolean(LOGIN_REMEMBER_ME,false)){
            startActivity(Intent(this,MainActivity::class.java))
        }
        listeners()
        observeState()
        observeMessage()
    }
    private fun observeMessage() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.message.collect{
                    when(it){
                        LoginMessageState.Idle ->{}
                        is LoginMessageState.Warning ->{
                            AlertDialog.Builder(this@LoginActivity)
                                .setTitle(R.string.warning)
                                .setIcon(R.drawable.baseline_warning_24)
                                .setMessage(it.message)
                                .create()
                                .show()
                        }
                        is LoginMessageState.Error ->{
                            AlertDialog.Builder(this@LoginActivity)
                                .setTitle(R.string.error)
                                .setIcon(R.drawable.baseline_error_24)
                                .setMessage(it.message)
                                .create()
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.state.collect{
                    when(it){
                        is LoginState.Idle ->{}
                        is LoginState.Loading ->{
                            binding.progressBar.isVisible = true
                        }
                        is LoginState.Success ->{
                            binding.progressBar.isVisible = false
                            if(binding.cbRememberMe.isChecked){
                                sharedPreferences.edit().putBoolean(LOGIN_REMEMBER_ME,true).apply()
                            }
                            val intent = Intent(this@LoginActivity,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        is LoginState.Error ->{
                            binding.progressBar.isVisible = false
                        }
                    }
                }
            }
        }
    }

    private fun listeners() {
        binding.tvSignUp.setOnClickListener {
            changeSwitchButton(binding.tvLogIn,binding.tvSignUp,binding.loginLayout,binding.signUpLayout,binding.btnLogin,binding.btnSignUp)
        }
        binding.tvLogIn.setOnClickListener {
            changeSwitchButton(binding.tvSignUp,binding.tvLogIn,binding.signUpLayout,binding.loginLayout,binding.btnSignUp,binding.btnLogin)
        }
        binding.btnLogin.setOnClickListener {
            viewModel.login(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }
        binding.btnSignUp.setOnClickListener {
            viewModel.signUp(binding.etNameSignUp.text.toString(), binding.etEmailSignUp.text.toString(), binding.etPasswordSignUp.text.toString(), binding.etPasswordConfirmSignUp.text.toString())
        }
    }

    private fun changeSwitchButton(lastSwitch: TextView, nextSwitch: TextView, hiddenLayout: LinearLayout, showLayout: LinearLayout, hiddenButton: Button, showButton: Button){
        nextSwitch.background = resources.getDrawable(R.drawable.switch_trcks,null)
        nextSwitch.setTextColor(resources.getColor(R.color.textColor,null))
        lastSwitch.background = null
        lastSwitch.setTextColor(resources.getColor(R.color.pinkColor,null))
        hiddenLayout.isVisible = false
        showLayout.isVisible = true
        hiddenButton.isVisible = false
        showButton.isVisible = true
    }
}