package com.vmadalin.dynamicfeatures.characterslist.ui.list.model

import com.vmadalin.core.mapper.Mapper
import com.vmadalin.core.network.responses.BaseResponse
import com.vmadalin.core.network.responses.CharacterResponse

private const val IMAGE_URL_FORMAT = "%s.%s"

class CharacterItemMapper : Mapper<BaseResponse<CharacterResponse>, List<CharacterItem>> {

    @Throws(NoSuchElementException::class)
    override fun map(from: BaseResponse<CharacterResponse>): List<CharacterItem> {
        return from.data.results.map {
            CharacterItem(
                id = it.id,
                name = it.name,
                description = it.description,
                imageUrl = IMAGE_URL_FORMAT.format(
                    it.thumbnail.path.replace("http", "https"),
                    it.thumbnail.extension
                )
            )
        }
    }
}
