package es.rafapuig.movieapp.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import es.rafapuig.movieapp.data.network.api.MovieService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkProvider(private val token: String) {

    private var retrofit: Retrofit? = null

    fun getRetrofit(): Retrofit {

        if (retrofit == null) {

            val client = OkHttpClient.Builder()
                .addInterceptor(RequestTokenInterceptor(token))
                .build()

            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        return retrofit!!
    }

    fun getMovieService() : MovieService {
        return getRetrofit().create(MovieService::class.java)
    }


}