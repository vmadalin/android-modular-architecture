package com.vmadalin.android.network.responses

data class CharacterResponse(
    var id: Int,
    var name: String,
    var description: String,
    var thumbnail: CharacterThumbnailResponse,
    var urls: CharacterUrlResponse
)
