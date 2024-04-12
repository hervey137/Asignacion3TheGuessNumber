package mx.edu.potros.theguessnumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var minValue = 0
    private var maxValue = 100
    private var num: Int = 0
    private var won = false

    private lateinit var title: TextView
    private lateinit var guessings: TextView
    private lateinit var down: Button
    private lateinit var up: Button
    private lateinit var generate: Button
    private lateinit var guessed: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = findViewById(R.id.title)
        guessings = findViewById(R.id.guessings)
        down = findViewById(R.id.down)
        up = findViewById(R.id.up)
        generate = findViewById(R.id.generate)
        guessed = findViewById(R.id.guessed)
        imageView = findViewById(R.id.imageView)

        generate.setOnClickListener {
            resetGame()
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        down.setOnClickListener {
            handleGuess(false)
        }

        guessed.setOnClickListener {
            if (!won) {
                guessings.text = getString(R.string.guess_result, num)
                guessed.text = getString(R.string.play_again)
                won = true
            } else {
                resetGame()
            }
        }

        up.setOnClickListener {
            handleGuess(true)
        }
    }

    private fun resetGame() {
        minValue = 0
        maxValue = 100
        num = Random.nextInt(minValue, maxValue)
        guessings.text = getString(R.string.tap_to_generate)
        imageView.setImageResource(R.drawable.ic_eyeball)
        won = false
    }

    private fun handleGuess(isHigher: Boolean) {
        if (!won) {
            if (isHigher) {
                minValue = num
            } else {
                maxValue = num
            }

            if (isGuessingValid()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.text = num.toString()
            } else {
                guessings.text = getString(R.string.lost_message)
            }
        }
    }

    private fun isGuessingValid(): Boolean {
        return minValue < maxValue
    }
}