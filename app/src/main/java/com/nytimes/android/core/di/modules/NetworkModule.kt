package com.nytimes.android.core.di.modules


import com.nytimes.android.BuildConfig
import com.nytimes.android.core.api.RestService
import com.nytimes.android.utils.GlobalVars
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun provideRestService(): RestService {
        val okHttpClientBuilder = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        okHttpClientBuilder.connectTimeout(40, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(40, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG)
            okHttpClientBuilder.addInterceptor(logging)

        val builder = Retrofit.Builder()
        Retrofit.Builder()
        builder.baseUrl(GlobalVars.DOMAIN)
        builder.addConverterFactory(GsonConverterFactory.create())
        builder.client(okHttpClientBuilder.build())


        return builder.build()
            .create(RestService::class.java)
    }

}
