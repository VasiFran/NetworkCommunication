package com.example.netexercise1

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.netexercise1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private var coroutine = CoroutineScope(Dispatchers.Main)
    var savedNumber: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener{
            val userNumber: Int? = binding.editNumber.text.toString().toIntOrNull()
            if(userNumber != null){
                coroutine.launch {
                    delay(2000)
                    if (savedNumber == null) {
                        savedNumber = userNumber + 1
                        binding.textviewNumber.text = savedNumber.toString()
                    } else {
                        savedNumber = savedNumber!! + 1
                        binding.textviewNumber.text = savedNumber.toString()
                    }
                }
            }
        }
    }
}
