package com.example.casestudy.adapters

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.casestudy.databinding.ScoreItemBinding
import com.example.casestudy.models.ScoreModel
import java.util.*

class ScoreAdapter : RecyclerView.Adapter<ScoreAdapter.ViewHolder>() {
    private val listScore = mutableListOf<ScoreModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ScoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listScore[position])
    }

    override fun getItemCount(): Int =listScore.size

    fun addNewScore(scoreModel: ScoreModel){
        // If there would be more than 10 entries, the last entry should be deleted from the list.
        if(listScore.size == 10){
            listScore.removeLast();
            notifyItemRemoved(listScore.size)
        }
        listScore.add(scoreModel)
        listScore.sort()
        listScore.reverse()
        notifyItemInserted(listScore.indexOf(scoreModel))
    }

    class ViewHolder(private val binding: ScoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(scoreModel: ScoreModel) {
            binding.isTitle = false
            binding.score = scoreModel.score.toString()

            binding.gameTime = DateFormat.format("hh:mm:ss", scoreModel.gameTime).toString()
            binding.date = DateFormat.format("MM/dd/yyyy", scoreModel.date).toString()

            binding.move = scoreModel.move.toString()
        }
    }
}