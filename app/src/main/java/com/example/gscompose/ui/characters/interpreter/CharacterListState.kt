package com.example.gscompose.ui.characters.interpreter

import com.example.gscompose.data.GSCharacter

/**
 * This data class will contain all
 * data we need to show in the UI side.
 * **/
data class CharacterListState(
    val characterList: List<GSCharacter>
)