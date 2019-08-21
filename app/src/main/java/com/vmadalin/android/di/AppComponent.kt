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

package com.vmadalin.android.di

import com.vmadalin.android.SampleApp
import com.vmadalin.android.di.modules.AppModule
import com.vmadalin.android.di.modules.NetworkModule
import com.vmadalin.android.di.scopes.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, NetworkModule::class])
interface AppComponent : AndroidInjector<SampleApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: SampleApp): AppComponent
    }
}
