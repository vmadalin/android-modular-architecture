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

import com.vmadalin.core.di.scopes.FeatureScope
import com.vmadalin.core.extensions.viewModel
import com.vmadalin.core.network.repositiories.MarvelRepository
import com.vmadalin.dynamicfeatures.characterslist.ui.list.CharactersListFragment
import com.vmadalin.dynamicfeatures.characterslist.ui.list.CharactersListViewModel
import com.vmadalin.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSourceFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
class CharactersListModule(private val fragment: CharactersListFragment) {

    @FeatureScope
    @Provides
    fun providesCharactersListViewModel(
        dataFactory: CharactersPageDataSourceFactory
    ): CharactersListViewModel {
        return fragment.viewModel {
            CharactersListViewModel(dataFactory)
        }
    }

    @FeatureScope
    @Provides
    fun providesCharactersPageDataSourceFactory(
        repository: MarvelRepository
    ): CharactersPageDataSourceFactory {
        return CharactersPageDataSourceFactory(
            repository,
            CoroutineScope(Dispatchers.IO)
        )
    }
}
