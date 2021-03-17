package seersolutions.base.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import seersolutions.base.BuildConfig
import seersolutions.base.data.network.RestAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RestServiceModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        return client.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()

            .addInterceptor(logging)

        return Retrofit.Builder()
            .client(httpClient.build()!!)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(BuildConfig.API_NYTIMES)
            .build()
    }


    @Provides
    @Singleton
    internal fun providePostApi(retrofitClient: Retrofit): RestAPI {
        return retrofitClient.create(RestAPI::class.java)
    }


}
