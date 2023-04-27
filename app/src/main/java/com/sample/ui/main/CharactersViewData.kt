package com.sample.ui.main

data class CharactersViewData(
    val heading: String,
    val relatedTopics: Array<CharacterViewData>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharactersViewData

        if (heading != other.heading) return false
        if (!relatedTopics.contentEquals(other.relatedTopics)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = heading.hashCode()
        result = 31 * result + relatedTopics.contentHashCode()
        return result
    }
}

data class CharacterViewData(
    val name: String
)
