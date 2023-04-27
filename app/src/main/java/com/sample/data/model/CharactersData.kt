package com.sample.data.model

data class CharacterData(
    val firstUrl: String,
    val icon: CharacterIconData,
    val text: String,
    val name: String
)

data class CharacterIconData(
    val url: String,
)
