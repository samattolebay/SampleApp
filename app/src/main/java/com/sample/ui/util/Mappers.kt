package com.sample.ui.util

import com.sample.data.model.CharacterData
import com.sample.data.model.CharactersData
import com.sample.ui.main.CharacterViewData
import com.sample.ui.main.CharactersViewData

fun CharactersData.toCharactersViewData() =
    CharactersViewData(heading, relatedTopics.map { it.toCharacterViewData() }.toTypedArray())

// TODO get name from url
fun CharacterData.toCharacterViewData() = CharacterViewData(this.firstUrl)

