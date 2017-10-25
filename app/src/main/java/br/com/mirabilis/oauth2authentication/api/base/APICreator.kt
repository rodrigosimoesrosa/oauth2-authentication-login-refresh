package br.com.mirabilis.oauth2authentication.api.base

import br.com.mirabilis.oauth2authentication.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by rodrigosimoesrosa
 */
class APICreator<out API>(private val clazz: Class<API>,
                          private val baseUrl: String,
                          private var writeTimeout: Long = 30,
                          private var readTimeout: Long = 30,
                          private var headers: HashMap<String, String> = HashMap(),
                          private var converterFactory: Converter.Factory? = GsonConverterFactory.create(),
                          private var level: HttpLoggingInterceptor.Level? = HttpLoggingInterceptor.Level.HEADERS) {

    private fun getOkHttpBuilder(writeTimeout: Long, readTimeout: Long): OkHttpClient.Builder {
        return OkHttpClient.Builder()
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
    }

    fun generate(): API {
        val okHttpClient = getOkHttpBuilder(writeTimeout, readTimeout)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = level
            okHttpClient.addInterceptor(loggingInterceptor)
        }

        okHttpClient.addNetworkInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()

            headers.map {
                requestBuilder.addHeader(it.key, it.value)
            }

            requestBuilder.method(original.method(), original.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        val client = okHttpClient.build()
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(converterFactory!!)
                .build()
        return retrofit.create(clazz)
    }
}