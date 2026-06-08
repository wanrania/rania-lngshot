package com.example.rania_lngshot.onboarding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rania_lngshot.R
import com.example.rania_lngshot.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup ViewPager dengan adapter
        val fragmentsList = listOf(OnBoarding1Fragment(), OnBoarding2Fragment(), OnBoarding3Fragment())
        val adapter = OnboardingFragmentAdapter(this, fragmentsList)
        binding.tutorialMessageViewPager.adapter = adapter
        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)
    }

}