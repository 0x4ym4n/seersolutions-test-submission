package seersolutions.login.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import seersolutions.login.component.ViewModelKey
import seersolutions.login.ui.LoginViewModel


@Module
interface ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel


}