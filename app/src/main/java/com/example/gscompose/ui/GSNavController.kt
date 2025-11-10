package com.example.gscompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gscompose.ui.characters.view.CharacterListScreenRoot

@Composable
fun GSNavController(navController: NavHostController) {
    /**
     * Navigation host "source of truth" of
     * all screens in the App.
     * **/
    NavHost(navController, startDestination = CharacterList) {
        composable<CharacterList> { backStackEntry ->
            CharacterListScreenRoot(
                onNavigateToCharacterDetails = {

                }
            )
        }
    }
}
