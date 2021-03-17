package seersolutions.login.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import seersolutions.login.LoginActivity


@Module
interface ActivityModule {


    @ContributesAndroidInjector
    fun contributeLoginActivity(): LoginActivity
}