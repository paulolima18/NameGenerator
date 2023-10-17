package com.paulolima.namegenerator.network

import retrofit2.http.GET

interface BabyNameService {

    @GET("name_db.json")
    suspend fun getDataset(): List<List<String>>
}