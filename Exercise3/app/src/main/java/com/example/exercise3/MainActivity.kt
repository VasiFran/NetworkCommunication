package com.example.exercise3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.example.exercise3.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    lateinit var norrisApi: NorrisApi
    val coroutineScope = CoroutineScope(Dispatchers.Main)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        norrisApi = ApiClient
            .create("34b66b3b7bmshbba952a20f03799p1ed047jsne378e2e696dc")
            .create(NorrisApi::class.java)

        coroutineScope.launch {
            val response: Quote = norrisApi.getQuotes()
            binding.quote.text = response.value
        }
    }
}