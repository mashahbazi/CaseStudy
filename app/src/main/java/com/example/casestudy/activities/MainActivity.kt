package com.example.casestudy.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.casestudy.adapters.ScoreAdapter
import com.example.casestudy.databinding.ActivityMainBinding
import com.example.casestudy.models.ScoreModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private val adapter = ScoreAdapter()
    private var lastScoreId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setUpRecyclerView(binding)
        setUpButtonDone(binding)
        setContentView(binding.root)
    }

    private fun setUpButtonDone(binding: ActivityMainBinding) {
        binding.btnDone.setOnClickListener {
            val scoreModel = generateScore()
            adapter.addNewScore(scoreModel)
        }
    }

    private fun setUpRecyclerView(binding: ActivityMainBinding) {
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter
    }

    private fun generateScore(): ScoreModel {
        lastScoreId++
        val random = Random()

        // Score is a random integer number from 0-999 range
        val score = random.nextInt(999)
        // Moves is a random number from [ score - 100 ; score + 100 ] range but no less than 0
        val move =
            random.nextInt(score + 100 - kotlin.math.max(score - 100, 0)) + kotlin.math.max(score - 100, 0)

        // Game time is a random time between 1 min – 2hr (up to a single second)
        val gameTimeRandS = random.nextInt((2 * 60 * 60) - 60) + 60
        val gameTimeCalendar = Calendar.getInstance()
        gameTimeCalendar.timeInMillis = gameTimeRandS * 1000L
        val gameTime = gameTimeCalendar.time

        // Date is the current system date
        val currentTime = Calendar.getInstance().time


        return ScoreModel(lastScoreId, score, gameTime, currentTime, move)
    }
}