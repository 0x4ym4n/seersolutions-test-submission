package seersolutions.dashboard.di.component

import dagger.Component
import seersolutions.base.di.component.BaseComponent
import seersolutions.dashboard.ActivityDashboard
import seersolutions.dashboard.di.module.ViewModelFactoryModule
import seersolutions.dashboard.di.module.ViewModelModule
import seersolutions.dashboard.di.scopes.FeatureOneScope

@FeatureOneScope
@Component(
    dependencies = [BaseComponent::class],
    modules = [ViewModelFactoryModule::class, ViewModelModule::class]
)
interface FeatureOneComponent {

    fun inject(activity: ActivityDashboard)

}