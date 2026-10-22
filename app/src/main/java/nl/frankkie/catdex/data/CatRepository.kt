package nl.frankkie.catdex.data

import kotlinx.serialization.json.Json
import nl.frankkie.catdex.BuildConfig
import nl.frankkie.catdex.model.Breed
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlin.time.Duration.Companion.seconds

class CatRepository(
    val apiKey: String,
    val baseUrl: String
) {

    private var okHttpClient: OkHttpClient
    private var loggingInterceptor: HttpLoggingInterceptor
    private var retrofit: Retrofit
    private var catApiService: CatApiService

    init {
        loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            // Add API Key as header with every call
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-api-key", apiKey)
                    .build()
                chain.proceed(request)
            }
            .connectTimeout(30.seconds)
            .build()

        // Json parser
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true // set to null if there's no default value
        }
        val contentType = "application/json".toMediaType()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        catApiService = retrofit.create(CatApiService::class.java)
    }

    suspend fun listBreeds(page: Int = 1, limit: Int = 12): List<Breed>? {
        try {
            val response = catApiService.listBreeds(page, limit).execute()

            if (response.isSuccessful) {
                return response.body()
            }

        } catch (e: IOException) {

        } catch (e: HttpException) {

        }
        return null
    }

    companion object {
        const val BASE_URL_BETA = "https://beta-api.thecatapi.com/v1/"
        const val BASE_URL_PROD = "https://api.thecatapi.com/v1/"
    }

}
