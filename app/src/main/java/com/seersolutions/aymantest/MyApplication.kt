package com.seersolutions.aymantest

import android.app.Application
import android.content.Context
import seersolutions.base.di.BaseComponentProvider
import seersolutions.base.di.component.BaseComponent
import seersolutions.base.di.component.DaggerBaseComponent
import seersolutions.base.di.module.BaseModule

class MyApplication : Application(), BaseComponentProvider {

    lateinit var baseComponent: BaseComponent

    override fun onCreate() {
        super.onCreate()

        baseComponent = DaggerBaseComponent
            .builder()
            .baseModule(BaseModule(this))
            .build()
        baseComponent.inject(this)
    }

    override fun provideBaseComponent(): BaseComponent {
        return baseComponent
    }

    override fun provideContext(): Context {
        return this
    }

}