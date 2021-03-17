package seersolutions.base.utils

import android.content.Context
import seersolutions.base.di.BaseComponentProvider
import seersolutions.base.di.component.BaseComponent

object InjectUtils {

    fun provideBaseComponent(applicationContext: Context): BaseComponent {
        return if (applicationContext is BaseComponentProvider) {
            (applicationContext as BaseComponentProvider).provideBaseComponent()
        } else {
            throw IllegalStateException("application context is not found")
        }
    }

}