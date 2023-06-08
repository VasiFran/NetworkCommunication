package com.example.exercise2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise2.databinding.ListJokesBinding

class JokeAdapter(val joke: List<Joke>) : RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ListJokesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Joke) {
            binding.jokesSetup.text = item.setup
            binding.jokesPunchline.text = item.punchline
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListJokesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(joke[position])
    }

    override fun getItemCount(): Int {
        return joke.size
    }
}