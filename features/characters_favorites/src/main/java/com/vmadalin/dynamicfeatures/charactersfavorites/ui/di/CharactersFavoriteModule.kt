package com.vmadalin.dynamicfeatures.charactersfavorites.ui.di

import com.vmadalin.core.database.characterfavorite.CharacterFavoriteRepository
import com.vmadalin.core.di.scopes.FeatureScope
import com.vmadalin.core.extensions.viewModel
import com.vmadalin.core.network.repositiories.MarvelRepository
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteAdapter
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteFragment
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteViewModel
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
class CharactersFavoriteModule(private val fragment: CharactersFavoriteFragment) {

    @Provides
    @FeatureScope
    fun providesCharactersFavoriteViewModel(
        characterFavoriteRepository: CharacterFavoriteRepository
    ): CharactersFavoriteViewModel {
        return fragment.viewModel {
            CharactersFavoriteViewModel(
                characterFavoriteRepository = characterFavoriteRepository,
                coroutineScope = CoroutineScope(Dispatchers.IO)
            )
        }
    }

    @Provides
    @FeatureScope
    fun providesCharactersFavoriteAdapter() = CharactersFavoriteAdapter()

}
