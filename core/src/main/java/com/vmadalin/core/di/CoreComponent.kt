package com.vmadalin.core.di

import android.app.Application
import com.vmadalin.core.di.modules.CoreModule
import com.vmadalin.core.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 */
@Singleton
@Component(modules = [CoreModule::class, NetworkModule::class])
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): CoreComponent
    }
}
