package com.example.memoria

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
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
            gameField.numColumns = 2
            gameField.isEnabled = true
            val gameFieldAdapter = GridAdapter(this@GameActivity, 2, 2, "football")

            menuButton.setOnClickListener {
                startActivity<MainActivity>()
            }

            replayButton.setOnClickListener {
                gameResult.visibility = View.GONE
                recreate()
            }

            gameField.adapter = gameFieldAdapter

            gameField.onItemClickListener = OnItemClickListener { _, _, position, _ ->
                gameFieldAdapter.checkOpenCells()
                gameFieldAdapter.openCell(position)
                if (gameFieldAdapter.isGameOver()) {
                    gameField.visibility = View.GONE
                    gameResult.visibility = View.VISIBLE
                }
            }
        }
    }
}