package com.example.gscompose.data.repository

import com.example.gscompose.data.GSCharacter
import com.example.gscompose.data.network.RickMortyApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface CharacterRepository {
    fun getCharacters() : Flow<List<GSCharacter>>
}

class CharacterRepositoryImpl(
    private val rickMortyApiService: RickMortyApiService
) : CharacterRepository {

    override fun getCharacters(): Flow<List<GSCharacter>> =
        flow {
            val data = rickMortyApiService.getCharacters()
            emit(data)
        }.flowOn(Dispatchers.IO)
}
