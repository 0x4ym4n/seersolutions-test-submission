package seersolutions.base.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class BaseModule(val application: Application) {

    // needed by Room database
    @Provides
    fun provideApplication(): Application {
        return application
    }

    // needed by Room database
    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app.applicationContext

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("pref", Context.MODE_PRIVATE)


}