package com.sample.ui.util

import com.sample.data.model.CharacterData
import com.sample.ui.main.CharacterViewData

// TODO get name from url
fun CharacterData.toCharacterViewData() = CharacterViewData(this.firstUrl)

fun List<CharacterData>.toCharacterViewDataList() = map { it.toCharacterViewData() }
