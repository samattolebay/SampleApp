package com.sample.data.network.model

import com.google.gson.annotations.SerializedName

data class CharactersResponseData(
    @SerializedName("Heading")
    val heading: String,
    @SerializedName("RelatedTopics")
    val relatedTopics: Array<CharacterResponseData>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharactersResponseData

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

data class CharacterResponseData(
    @SerializedName("FirstURL")
    val firstUrl: String,
    @SerializedName("Icon")
    val icon: CharacterIconResponseData,
    @SerializedName("Text")
    val text: String,
)

data class CharacterIconResponseData(
    @SerializedName("URL")
    val url: String,
)
