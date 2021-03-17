package seersolutions.login.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import seersolutions.login.component.DaggerViewModelFactory

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

}