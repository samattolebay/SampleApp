package com.sample.ui.util

import com.sample.data.model.CharacterData
import com.sample.ui.main.CharacterViewData

fun CharacterData.toCharacterViewData() = CharacterViewData(name)

fun List<CharacterData>.toCharacterViewDataList() = map { it.toCharacterViewData() }
