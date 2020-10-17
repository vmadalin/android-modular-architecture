/*
 * Copyright 2019 vmadalin.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vmadalin.dynamicfeatures.characterslist.ui.list.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.viewModelScope
import com.vmadalin.commons.ui.extensions.viewModel
import com.vmadalin.core.di.scopes.FeatureScope
import com.vmadalin.core.network.repositiories.MarvelRepository
import com.vmadalin.dynamicfeatures.characterslist.ui.list.CharactersListFragment
import com.vmadalin.dynamicfeatures.characterslist.ui.list.CharactersListViewModel
import com.vmadalin.dynamicfeatures.characterslist.ui.list.adapter.CharactersListAdapter
import com.vmadalin.dynamicfeatures.characterslist.ui.list.model.CharacterItemMapper
import com.vmadalin.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSource
import com.vmadalin.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSourceFactory
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [CharactersListComponent].
 *
 * @see Module
 */
@Module
class CharactersListModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: CharactersListFragment
) {

    /**
     * Create a provider method binding for [CharactersListViewModel].
     *
     * @param dataFactory Data source factory for characters.
     * @return Instance of view model.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharactersListViewModel(
        dataFactory: CharactersPageDataSourceFactory
    ) = fragment.viewModel {
        CharactersListViewModel(dataFactory)
    }

    /**
     * Create a provider method binding for [CharactersPageDataSource].
     *
     * @return Instance of data source.
     * @see Provides
     */
    @Provides
    fun providesCharactersPageDataSource(
        viewModel: CharactersListViewModel,
        repository: MarvelRepository,
        mapper: CharacterItemMapper
    ) = CharactersPageDataSource(
        repository = repository,
        scope = viewModel.viewModelScope,
        mapper = mapper
    )

    /**
     * Create a provider method binding for [CharacterItemMapper].
     *
     * @return Instance of mapper.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharacterItemMapper() = CharacterItemMapper()

    /**
     * Create a provider method binding for [CharactersListAdapter].
     *
     * @return Instance of adapter.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharactersListAdapter(
        viewModel: CharactersListViewModel
    ) = CharactersListAdapter(viewModel)
}
