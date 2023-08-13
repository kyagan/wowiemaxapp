package com.kaanyagan.wowiemax.ui.empty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kaanyagan.wowiemax.R
import com.kaanyagan.wowiemax.databinding.ActivityEmptyBinding

class EmptyActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmptyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmptyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}