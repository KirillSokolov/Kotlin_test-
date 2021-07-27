package aeon.test.task.restapi

import aeon.test.task.BuildConfig
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    private val cacheSize = 10L * 1024L * 1024L // 10MB
    private lateinit var gson: Gson

    //https://favqs.com/api/
    //https://proglib.io/p/7-besplatnyh-api-o-kotoryh-nikto-ne-govorit-2020-12-07
    //https://apptractor.ru/info/articles/10-rest-api.html
    private val baseUrl: String = "http://82.202.204.94/api-test/"

    fun <T> createService(serviceClass: Class<T>, baseUrl: String, context: Context): T {
        return createService(serviceClass, context)
    }

    fun <T> createService(serviceClass: Class<T>,  context: Context): T {
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            if (context.cacheDir != null) httpClient.cache(
                Cache(
                    context.cacheDir,
                    cacheSize
                )
            )
            try {
                httpClient.addInterceptor(
                    DebugTool.createChuckInterceptor(
                        context
                    )
                )
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
        var retrofit: Retrofit? =
            builder.addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit!!.create(serviceClass)
    }
}