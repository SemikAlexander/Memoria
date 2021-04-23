package com.example.memoria

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.memoria.adapters.GridAdapter
import com.example.memoria.databinding.ActivityGameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class GameActivity : AppCompatActivity() {

    lateinit var binding: ActivityGameBinding

    private var size = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.apply {
            when (intent.getStringExtra("difficult")) {
                "easy" -> size = 4
                "medium" -> size = 6
                "hard" -> size = 8
            }

            gameField.numColumns = size
            gameField.isEnabled = true
            val gameFieldAdapter =
                GridAdapter(this@GameActivity, size, size, "football")

            menuButton.setOnClickListener {
                finish()
            }

            replayButton.setOnClickListener {
                gameResult.visibility = View.GONE
                recreate()
            }

            gameField.adapter = gameFieldAdapter

            gameField.onItemClickListener = OnItemClickListener { _, _, position, _ ->
                gameFieldAdapter.checkOpenCells()
                gameFieldAdapter.openCell(position)

                GlobalScope.launch {
                    delay(1000)
                    launch(Dispatchers.Main) {
                        gameFieldAdapter.checkOpenCells()
                    }
                }
                if (gameFieldAdapter.isGameOver()) {
                    gameField.visibility = View.GONE
                    gameResult.visibility = View.VISIBLE
                }
            }
        }
    }

}