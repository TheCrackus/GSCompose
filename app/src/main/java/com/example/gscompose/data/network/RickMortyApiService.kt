package com.example.gscompose.data.network

import com.example.gscompose.data.GSCharacter
import retrofit2.http.GET

interface RickMortyApiService {
    @GET("character/1,2,3,4,5,6,7,8,9,10")
    suspend fun getCharacters(): List<GSCharacter>
}