package com.example.memoria

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.memoria.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        MobileAds.initialize(this) {}

        binding.apply {
            startGame.setOnClickListener {
                startActivity<GameActivity>()
            }

            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
        }
    }
}