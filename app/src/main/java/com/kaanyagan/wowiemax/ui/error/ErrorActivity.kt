package com.kaanyagan.wowiemax.ui.error

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kaanyagan.wowiemax.databinding.ActivityErrorBinding

class ErrorActivity : AppCompatActivity() {
    lateinit var binding: ActivityErrorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}