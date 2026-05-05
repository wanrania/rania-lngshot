package com.example.rania_lngshot.home.pertemuan_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rania_lngshot.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USERNAME")

        binding.tvWelcome.text = "Selamat Datang, $username 🩷"
    }
}