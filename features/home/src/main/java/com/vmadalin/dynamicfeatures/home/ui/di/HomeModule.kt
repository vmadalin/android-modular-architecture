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

package com.vmadalin.dynamicfeatures.home.ui.di

import com.vmadalin.core.di.scopes.FeatureScope
import com.vmadalin.core.extensions.viewModel
import com.vmadalin.dynamicfeatures.home.ui.HomeFragment
import com.vmadalin.dynamicfeatures.home.ui.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeModule(private val fragment: HomeFragment) {

    @Provides
    @FeatureScope
    fun providesHomeViewModel(): HomeViewModel {
        return fragment.viewModel {
            HomeViewModel()
        }
    }
}
