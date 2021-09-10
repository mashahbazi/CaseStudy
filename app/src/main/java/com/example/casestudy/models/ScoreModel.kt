package com.example.casestudy.models

import java.util.*

class ScoreModel(
    private val id: Int,
    val score: Int,
    val gameTime: Date,
    val date: Date,
    val move: Int
) :
    Comparable<ScoreModel> {

    /**
     * sorted according to the score (the higher score, the higher position).
     * Tied score should be sorted according to number of moves (the lower moves, the higher position).
     * Tied score and moves should be sorted according to the time (the shorter game time, the higher position).
     * If all those values are tied, the new entry should be added below the tied entry
     */
    override fun compareTo(other: ScoreModel): Int {
        var result = score.compareTo(other.score)
        if (result == 0) {
            result = other.move.compareTo(move)
        }
        if (result == 0) {
            result = other.gameTime.compareTo(gameTime)
        }
        if (result == 0) {
            result = id.compareTo(other.id)
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        return other != null && other is ScoreModel && other.hashCode() == hashCode()
    }

    override fun hashCode(): Int {
        return id
    }
}