package seersolutions.base.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import seersolutions.base.data.room.AppDatabase
import javax.inject.Singleton


@Module
class RoomModule {


    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application) =
        Room.databaseBuilder(app, AppDatabase::class.java, "seersolutions-test").build()

    @Provides
    @Singleton
    fun provideRoomDao(appDatabase: AppDatabase) = appDatabase.roomDoa()


}