package com.example.memoria

import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.memoria.adapters.GridAdapter
import com.example.memoria.databinding.ActivityGameBinding


class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.apply {
            gameField.numColumns = 6
            gameField.isEnabled = true

            val gameFieldAdapter = GridAdapter(this@GameActivity, 6, 6, "football")
            gameField.adapter = gameFieldAdapter

            gameField.onItemClickListener = OnItemClickListener { _, _, position, _ ->
                gameFieldAdapter.checkOpenCells()
                gameFieldAdapter.openCell(position)
                if (gameFieldAdapter.checkGameOver()) Toast.makeText(applicationContext, "Игра закончена", Toast.LENGTH_SHORT).show()
            }
        }
    }
}