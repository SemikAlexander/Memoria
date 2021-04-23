package com.example.memoria

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.memoria.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var level = "easy"
    var user_topic = "football"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @SuppressLint("ResourceAsColor")
    override fun onStart() {
        super.onStart()

        binding.apply {
            startGame.setOnClickListener {
                startActivity<GameActivity> {
                    putExtra("difficult", level)
                }
            }

            easyButton.setOnClickListener{
                level = "easy"

                easyButton.setBackgroundColor(
                    ContextCompat.getColor(this@MainActivity, R.color.selected_background))
                mediumButton.setBackgroundColor(
                    ContextCompat.getColor(this@MainActivity, R.color.black_background))
                hardButton.setBackgroundColor(
                    ContextCompat.getColor(this@MainActivity, R.color.black_background))
            }

            mediumButton.setOnClickListener{
                level = "medium"

                easyButton.setBackgroundColor(
                    ContextCompat.getColor(this@MainActivity, R.color.black_background))
                mediumButton.setBackgroundColor(
                    ContextCompat.getColor(this@MainActivity, R.color.selected_background))
                hardButton.setBackgroundColor(
                    ContextCompat.getColor(this@MainActivity, R.color.black_background))

            }

            hardButton.setOnClickListener{
                level = "hard"

                easyButton.setBackgroundColor(
                    ContextCompat.getColor(this@MainActivity, R.color.black_background))
                mediumButton.setBackgroundColor(
                    ContextCompat.getColor(this@MainActivity, R.color.black_background))
                hardButton.setBackgroundColor(
                    ContextCompat.getColor(this@MainActivity, R.color.selected_background))
            }

            adView.loadAd(AdRequest.Builder().build())
        }
    }

}