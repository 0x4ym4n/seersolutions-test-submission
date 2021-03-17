package seersolutions.dashboard.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import seersolutions.dashboard.di.component.ViewModelKey
import seersolutions.dashboard.ui.DashboardViewModel


@Module
interface ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel




}