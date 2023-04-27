package com.sample.data.model

data class CharactersData(
    val heading: String,
    val relatedTopics: Array<CharacterData>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharactersData

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

data class CharacterData(
    val firstUrl: String,
    val icon: CharacterIconData,
    val text: String,
)

data class CharacterIconData(
    val url: String,
)
