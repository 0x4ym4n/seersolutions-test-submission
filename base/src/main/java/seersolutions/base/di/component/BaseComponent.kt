package seersolutions.base.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Component
import seersolutions.base.data.network.RestAPI
import seersolutions.base.data.repository.SeerSolutionsRepository
import seersolutions.base.data.room.RoomDoa
import seersolutions.base.di.module.BaseModule
import seersolutions.base.di.module.RepositoryModule
import seersolutions.base.di.module.RestServiceModule
import seersolutions.base.di.module.RoomModule
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [BaseModule::class,
        RestServiceModule::class,
        RoomModule::class,
        RepositoryModule::class


    ]
)


interface BaseComponent {


    fun inject(app: Application)


    fun getRepository(): SeerSolutionsRepository


    fun getPostApi(): RestAPI


    fun getRetrofit(): Retrofit


    fun getContext(): Context


    fun getOkHttpClient(): OkHttpClient


    fun getRoomDao(): RoomDoa


    fun getSharedPreferences(): SharedPreferences

}