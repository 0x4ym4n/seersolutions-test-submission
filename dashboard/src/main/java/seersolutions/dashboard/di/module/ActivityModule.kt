package seersolutions.dashboard.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import seersolutions.dashboard.ActivityDashboard


@Module
interface ActivityModule {


    @ContributesAndroidInjector
    fun contributeActivityDashboard(): ActivityDashboard

}