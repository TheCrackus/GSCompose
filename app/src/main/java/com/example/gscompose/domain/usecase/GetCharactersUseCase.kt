package com.example.gscompose.domain.usecase

import com.example.gscompose.data.GSCharacter
import com.example.gscompose.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(): Flow<List<GSCharacter>> {
        return characterRepository.getCharacters()
    }
}