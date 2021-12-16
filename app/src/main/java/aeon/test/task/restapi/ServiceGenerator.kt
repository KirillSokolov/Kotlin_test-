package aeon.test.task.restapi

import aeon.test.task.BuildConfig
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    private val cacheSize = 10L * 1024L * 1024L // 10MB
    private lateinit var gson: Gson

    private var baseUrl: String = "https://app.passport.com.ru"

    fun <T> createService(serviceClass: Class<T>, url: String, context: Context): T {
        baseUrl = url
        return createService(serviceClass, context)
    }

    fun <T> createService(serviceClass: Class<T>,  context: Context): T {
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            if (context.cacheDir != null) httpClient.cache(Cache(context.cacheDir, cacheSize))
            try {
                httpClient.addInterceptor(DebugTool.createChuckInterceptor(context))
                httpClient.addInterceptor(AuthorizationInterceptor())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
        builder.client(httpClient.build())
        gson = GsonBuilder().setLenient().create()
        val retrofit: Retrofit? =
            builder.addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

        return retrofit!!.create(serviceClass)
    }
}