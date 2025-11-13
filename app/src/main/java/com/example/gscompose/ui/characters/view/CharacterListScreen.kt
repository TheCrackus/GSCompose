package com.example.gscompose.ui.characters.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.gscompose.data.GSCharacter
import com.example.gscompose.ui.characters.interpreter.CharacterListAction
import com.example.gscompose.ui.characters.interpreter.CharacterListEvent
import com.example.gscompose.ui.characters.interpreter.CharacterListState
import com.example.gscompose.ui.characters.viewmodel.CharacterListViewModel
import com.example.gscompose.ui.components.CharacterItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

/**
 * For convention we must use three
 * components in each compose screen:
 * - ScreenRoot: Root used in navigation references
 * - Screen: UI container
 * - ScreenPreview: Preview for AS tools.
 * **/

@Composable
fun CharacterListScreenRoot(
    onNavigateToCharacterDetails: (GSCharacter) -> Unit,
    characterListViewModel: CharacterListViewModel = koinViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(characterListViewModel.oneTimeEvent, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                characterListViewModel.oneTimeEvent.collect { event ->
                    when (event) {
                        is CharacterListEvent.OnCharactersLoad ->
                            characterListViewModel.showCharacters(
                                characters = event.characters
                            )
                    }
                }
            }
        }
    }
    CharacterListScreen(
        characterListState = characterListViewModel.state,
        onAction = { action ->
            when (action) {
                is CharacterListAction.OnCharacterClick ->
                    onNavigateToCharacterDetails(
                        action.character
                    )
            }
            characterListViewModel
                .onAction(
                    characterListAction = action
                )
        }
    )
}

@Composable
fun CharacterListScreen(
    characterListState: CharacterListState,
    onAction: (CharacterListAction) -> Unit
) {
    /**
     *  The inner padding is applied to
     *  the whole screen preventing an
     *  overlaying to the system icons and bars.
    *  */
    Scaffold { innerpadding ->
        /**
         * A LazyColumn is a UI component that
         * allow us to show listed items on
         * the screen.
         * **/
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(characterListState.characterList) { character ->
                CharacterItem(
                    character = character,
                    onItemClick = { character ->
                        onAction(
                            CharacterListAction.OnCharacterClick(
                                character = character
                            )
                        )
                    }
                )
            }
        }
    }
}

/**
 * The Preview usually does not contain
 * any functionality or it depends on
 * what you want to see in AS previewer.
 * **/
@Preview(showBackground = true)
@Composable
fun CharacterListScreenPreview() {
    Box(
        Modifier.fillMaxSize()
    ) {
        CharacterListScreen(
            characterListState = CharacterListState(
                characterList = emptyList()
            ),
            onAction = {}
        )
    }
}
