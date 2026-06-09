package com.example.rania_lngshot.data.api

import com.example.rania_lngshot.data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}