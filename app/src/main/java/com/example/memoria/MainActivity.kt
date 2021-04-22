package com.example.memoria

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memoria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val pref = getSharedPreferences("setting", Context.MODE_PRIVATE)
        val editor = pref.edit()

        binding.apply {
            startGame.setOnClickListener {
                startActivity<GameActivity>()
            }
        }
    }
}