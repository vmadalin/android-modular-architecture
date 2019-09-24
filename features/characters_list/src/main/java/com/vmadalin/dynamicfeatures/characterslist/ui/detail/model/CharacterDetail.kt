package com.vmadalin.dynamicfeatures.characterslist.ui.detail.model

data class CharacterDetail(
    var id: Long,
    var name: String,
    var description: String,
    var imageUrl: String,

    var detailUrl: String? = null,
    var wikiUrl: String? = null,
    var comicUrl: String? = null
)
