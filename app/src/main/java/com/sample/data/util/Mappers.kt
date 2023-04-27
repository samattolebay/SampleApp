package com.sample.data.util

import com.sample.data.model.CharacterData
import com.sample.data.model.CharacterIconData
import com.sample.data.network.model.CharacterIconResponseData
import com.sample.data.network.model.CharacterResponseData

fun CharacterResponseData.toCharacterData() = CharacterData(
    firstUrl, icon.toCharacterIconData(), text
)

fun CharacterIconResponseData.toCharacterIconData() = CharacterIconData(url)
