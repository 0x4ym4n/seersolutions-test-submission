package seersolutions.dashboard.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import seersolutions.dashboard.di.component.DaggerViewModelFactory

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

}