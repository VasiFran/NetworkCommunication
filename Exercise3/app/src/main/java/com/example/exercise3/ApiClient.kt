package com.example.exercise3

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

    object ApiClient {


        fun create(apiKey: String): Retrofit {
            val client = OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor(apiKey))
                .build()

            return Retrofit.Builder()
                .baseUrl("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {

                val request = chain.request()
                val url: HttpUrl = request.url.newBuilder()
                    .addQueryParameter("key", "34b66b3b7bmshbba952a20f03799p1ed047jsne378e2e696dc")
                    .build()

                val newRequest = request.newBuilder()
                    .url(url)
                    .addHeader("X-RapidAPI-Key", apiKey)
                    .addHeader("X-RapidAPI-Host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                    .build()

                return chain.proceed(newRequest)
            }
        }
    }