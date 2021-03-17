package com.seersolutions.aymantest.main.di.component

import com.seersolutions.aymantest.SplashScreen
import com.seersolutions.aymantest.main.di.module.MainActivityModule
import com.seersolutions.aymantest.main.di.scopes.MainActivityScope
import dagger.Component
import seersolutions.base.di.component.BaseComponent

@MainActivityScope
@Component(
    dependencies = [BaseComponent::class],
    modules = [MainActivityModule::class]
)
interface MainActivityComponent {

    fun inject(activity: SplashScreen)

}