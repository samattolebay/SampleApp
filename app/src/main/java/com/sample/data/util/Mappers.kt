package com.sample.data.util

import com.sample.data.model.CharacterData
import com.sample.data.model.CharacterIconData
import com.sample.data.model.CharactersData
import com.sample.data.network.model.CharacterIconResponseData
import com.sample.data.network.model.CharacterResponseData
import com.sample.data.network.model.CharactersResponseData

fun CharactersResponseData.toCharactersData() = CharactersData(
    heading, relatedTopics.map { it.toCharacterData() }.toTypedArray()
)

fun CharacterResponseData.toCharacterData() = CharacterData(
    firstUrl, icon.toCharacterIconData(), text
)

fun CharacterIconResponseData.toCharacterIconData() = CharacterIconData(url)
