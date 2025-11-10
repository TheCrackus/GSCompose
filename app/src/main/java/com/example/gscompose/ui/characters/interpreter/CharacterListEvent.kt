package com.example.gscompose.ui.characters.interpreter

import com.example.gscompose.data.GSCharacter

sealed interface CharacterListEvent {
    data class OnCharactersLoad(
        val characters: List<GSCharacter>
    ): CharacterListEvent
}
