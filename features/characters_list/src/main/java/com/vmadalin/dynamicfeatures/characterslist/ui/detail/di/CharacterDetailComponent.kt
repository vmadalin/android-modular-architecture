package com.vmadalin.dynamicfeatures.characterslist.ui.detail.di

import com.vmadalin.core.di.CoreComponent
import com.vmadalin.core.di.scopes.FeatureScope
import com.vmadalin.dynamicfeatures.characterslist.ui.detail.CharacterDetailFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [CharacterDetailModule::class],
    dependencies = [CoreComponent::class])
interface CharacterDetailComponent {

    fun inject(detailFragment: CharacterDetailFragment)
}
