package com.example.randomfood.data.data_source.remote

import com.example.randomfood.data.data_source.remote.model.SingleRandomMealResponse
import retrofit2.Response
import retrofit2.http.GET

interface FreeMealApi {
    @GET("api/json/v1/1/random.php")
    suspend fun fetchSingleRandomMeal(): Response<SingleRandomMealResponse>
}
