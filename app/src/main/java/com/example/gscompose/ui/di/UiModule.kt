package com.example.gscompose.ui.di

import com.example.gscompose.ui.characterdetails.viewmodel.CharacterDetailsViewModel
import com.example.gscompose.ui.characters.viewmodel.CharacterListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::CharacterListViewModel)
    viewModelOf(::CharacterDetailsViewModel)
}
