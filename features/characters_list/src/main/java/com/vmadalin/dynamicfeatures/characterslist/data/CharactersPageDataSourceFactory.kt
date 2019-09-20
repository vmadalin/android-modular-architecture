package com.vmadalin.dynamicfeatures.characterslist.data

import androidx.paging.DataSource
import com.vmadalin.dynamicfeatures.characterslist.models.CharacterItem
import javax.inject.Inject

class CharactersPageDataSourceFactory
@Inject constructor(
    private val charactersPageDataSource: CharactersPageDataSource
) : DataSource.Factory<Int, CharacterItem>() {

    override fun create(): DataSource<Int, CharacterItem> {
        return charactersPageDataSource
    }
}

