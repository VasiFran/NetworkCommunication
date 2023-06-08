package com.example.exercise2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise2.databinding.ActivityMainBinding
import com.example.exercise2.ui.theme.Exercise2Theme
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface JokeApi {
    @GET("jokes/{type}/ten")
    suspend fun listJokes (@Path("type") type : String): List<Joke>
}

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    val retrofit = Retrofit.Builder()
        .baseUrl("https://official-joke-api.appspot.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val jokeApi = retrofit.create(JokeApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch{
            val jokes = jokeApi.listJokes("general")
            binding.jokesList.layoutManager = LinearLayoutManager(applicationContext)
            binding.jokesList.adapter = JokeAdapter(jokes)
        }
    }
}