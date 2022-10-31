package com.myu.myurickandmorty.ui.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myu.myurickandmorty.data.entities.Character
import com.myu.myurickandmorty.data.repository.CharacterRepository
import com.myu.myurickandmorty.utils.Resource

class CharactersViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : ViewModel(){

    val characters : LiveData<Resource<List<Character>>> = repository.getCharacters()

}