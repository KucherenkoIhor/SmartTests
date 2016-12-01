package ughtu.com.smarttests.shared

/**
 * Created by igor on 01.12.16.
 */

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
import javax.inject.Singleton


@Module
class NewsModule {

//    @Provides
//    @Singleton
//    fun provideNewsPresenter(): NewsPresenter {
//        return NewsPresenter()
//    }

    @Provides
    @Singleton
    internal fun provideNewApiInterface(): ApiInterface {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        return retrofit.create(ApiInterface::class.java)
    }
}