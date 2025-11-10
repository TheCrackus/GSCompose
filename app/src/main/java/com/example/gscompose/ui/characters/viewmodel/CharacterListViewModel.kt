package com.example.gscompose.ui.characters.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gscompose.data.GSCharacter
import com.example.gscompose.domain.usecase.GetCharactersUseCase
import com.example.gscompose.ui.characters.interpreter.CharacterListAction
import com.example.gscompose.ui.characters.interpreter.CharacterListEvent
import com.example.gscompose.ui.characters.interpreter.CharacterListState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Classic view model where we handle
 * all business logic.
 * **/
class CharacterListViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    var state by mutableStateOf(
        value = CharacterListState(
            characterList = emptyList()
        )
    )
        private set

    private val _oneTimeEvent = Channel<CharacterListEvent>()
    val oneTimeEvent = _oneTimeEvent.receiveAsFlow()

    /**
     * Action handler.
     *
     * We can place this into a T class
     * and make a contract to avoid
     * boilerplate code.
     * **/
    fun onAction(characterListAction: CharacterListAction) {
        when (characterListAction) {
            else -> {}
        }
    }

    fun loadCharactersInfo() {
        viewModelScope.launch {
            getCharactersUseCase.invoke().collect { characters ->
                if (characters.isNotEmpty()) {
                    _oneTimeEvent.send(
                        element = CharacterListEvent.OnCharactersLoad(
                            characters = characters
                        )
                    )
                }
            }
        }
    }

    fun showCharacters(
        characters: List<GSCharacter>
    ) {
        state = state.copy(
            characterList = characters
        )
    }

    init {
        loadCharactersInfo()
    }
}
