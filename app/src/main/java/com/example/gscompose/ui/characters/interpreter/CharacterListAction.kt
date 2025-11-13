package com.example.gscompose.ui.characters.interpreter

import com.example.gscompose.data.GSCharacter

/**
 * Actions that the user continuously does
 * as clicking buttons, swaps,closing bottom sheets,
 * closing dialogs, etc.
 * **/
sealed interface CharacterListAction {
    data class OnCharacterClick(
        val character: GSCharacter
    ): CharacterListAction
}
