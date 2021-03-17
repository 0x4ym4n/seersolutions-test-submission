package seersolutions.base.di

import android.content.Context
import seersolutions.base.di.component.BaseComponent

interface BaseComponentProvider {

    fun provideBaseComponent(): BaseComponent


    fun provideContext(): Context


}