package com.example.exercise3

import retrofit2.http.GET

interface NorrisApi {
    @GET ("/jokes/random")
    suspend fun getQuotes (): Quote
}