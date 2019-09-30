package com.vmadalin.dynamicfeatures.charactersfavorites.ui.di

import com.vmadalin.core.di.CoreComponent
import com.vmadalin.core.di.scopes.FeatureScope
import com.vmadalin.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [CharactersFavoriteModule::class],
    dependencies = [CoreComponent::class])
interface CharactersFavoriteComponent {

    fun inject(detailFragment: CharactersFavoriteFragment)
}
