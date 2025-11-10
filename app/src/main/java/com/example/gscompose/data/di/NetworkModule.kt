package com.example.gscompose.data.di

import com.example.gscompose.data.network.RickMortyApiService
import com.example.gscompose.data.repository.CharacterRepository
import com.example.gscompose.data.repository.CharacterRepositoryImpl
import com.example.gscompose.domain.usecase.GetCharactersUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL =
    "https://rickandmortyapi.com/api/"

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickMortyApiService::class.java)
    }
    singleOf(::CharacterRepositoryImpl) bind CharacterRepository::class
    singleOf(::GetCharactersUseCase)
}