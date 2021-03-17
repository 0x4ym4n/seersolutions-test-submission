package seersolutions.base.di.module

import dagger.Binds
import dagger.Module
import seersolutions.base.data.repository.SeerSolutionsDataRepository
import seersolutions.base.data.repository.SeerSolutionsRepository


@Module
interface RepositoryModule {

    @Binds
    fun bindRoomRepository(imp: SeerSolutionsDataRepository): SeerSolutionsRepository

}