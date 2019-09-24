package com.vmadalin.dynamicfeatures.characterslist.ui.list.di

import com.vmadalin.core.di.CoreComponent
import com.vmadalin.core.di.scopes.FeatureScope
import com.vmadalin.dynamicfeatures.characterslist.ui.list.CharactersListFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [CharactersListModule::class],
    dependencies = [CoreComponent::class])
interface CharactersListComponent {

    fun inject(listFragment: CharactersListFragment)
}
