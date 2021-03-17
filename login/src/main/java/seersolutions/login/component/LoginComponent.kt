package seersolutions.login.component

import dagger.Component
import seersolutions.base.di.component.BaseComponent
import seersolutions.login.LoginActivity
import seersolutions.login.module.ViewModelFactoryModule
import seersolutions.login.module.ViewModelModule
import seersolutions.login.scopes.LoginScope

@LoginScope
@Component(
    dependencies = [BaseComponent::class],
    modules = [ViewModelFactoryModule::class, ViewModelModule::class]
)
interface LoginComponent {

    fun inject(activity: LoginActivity)

}