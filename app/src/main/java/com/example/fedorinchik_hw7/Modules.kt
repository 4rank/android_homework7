package com.example.fedorinchik_hw7

import android.app.Application
import com.example.fedorinchik_hw7.viewmodel.GiphyViewModel
import com.example.fedorinchik_hw7.viewmodel.ViewModel
import okhttp3.Cache
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val moduleGiphyViewModel = module {
    viewModel { GiphyViewModel() }
}

val moduleViewModel = module {
    viewModel {
        ViewModel(get())
    }
}

val moduleApi = module {
    fun giveGiphyApi(retrofit: Retrofit): GiphyApi {
        return retrofit.create(GiphyApi::class.java)
    }
    single { giveGiphyApi(get()) }
}

val moduleURL = module {
    fun giveRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GiphConstants.RESOURSE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        giveRetrofit()
    }
    fun cache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }
    single {
        cache(get())
    }
}

val moduleRepository = module {
    fun giveRepository(api: GiphyApi): Repository {
        return Repository(api)
    }
    single {
        giveRepository(get())
    }
}
