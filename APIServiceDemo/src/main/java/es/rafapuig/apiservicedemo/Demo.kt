package es.rafapuig.apiservicedemo

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import es.rafapuig.apiservicedemo.api.MovieService
import es.rafapuig.apiservicedemo.model.MoviesResponse
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val API_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YjYwYmIwMWM5M2FlZDZjNjQz" +
        "YTlmNThkNWVmNTAzMCIsIm5iZiI6MTczNzU1MDc2Ni45NjcsInN1YiI6IjY3OTBlYmFlMmQ" +
        "2MWMzM2U2M2RmZmI4OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Fetk" +
        "nARzdQhKbAQXWyxiKAW4tH33zyzZNUIxUAQzbzM"

fun main() {
    //println(getMoviesJSON())
    //ejemplo2()
    //ejemploRetrofit()
    //ejemploRetrofitWithInterceptor()
    //ejemploRetrofitCoroutines()
    ejemploRetrofitCoroutines2()
}

fun getMoviesJSON() : String {

    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://api.themoviedb.org/3/movie/now_playing?language=es-ES")
        .get()
        .addHeader("Authorization","Bearer $API_TOKEN")
        .addHeader("Accept","application/json")
        .build()

    val call = client.newCall(request)

    val response = call.execute()

    val json = response.body?.string() ?: ""

    return json
}


@OptIn(ExperimentalStdlibApi::class)
fun ejemplo2() {

    val json = getMoviesJSON()

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val jsonAdapter = moshi.adapter<MoviesResponse>()

    val moviesResponse = jsonAdapter.fromJson(json)

    printMoviesTitle(moviesResponse)

}

private fun printMoviesTitle(moviesResponse: MoviesResponse?) {
    moviesResponse?.movieList?.forEach {
        println(it.title)
    }
}

fun ejemploRetrofit() {

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val movieService = retrofit.create(MovieService::class.java)

    val call = movieService.getNowPlayingMovies()

    val response = call.execute()

    val moviesResponse = response.body()

    printMoviesTitle(moviesResponse)
}

class RequestTokenInterceptor(private val token : String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder()
            .addHeader("Authorization","Bearer $token")
            .addHeader("Accept","application/json")
            .build()

        return chain.proceed(newRequest)
    }

}

fun ejemploRetrofitWithInterceptor() {

    val movieService = getMovieService()

    val call = movieService.getNowPlayingMovies()

    val response = call.execute()

    val moviesResponse = response.body()

    printMoviesTitle(moviesResponse)

}

private fun getMovieService(): MovieService {

    val client = OkHttpClient.Builder()
        .addInterceptor(RequestTokenInterceptor(API_TOKEN))
        .build()

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val movieService = retrofit.create(MovieService::class.java)
    return movieService
}

/*fun ejemploRetrofitCoroutines() {

    val service = getMovieService()

    runBlocking {
        val response = service.getNowPlayingMoviesAsyncResponse()
        if(response.isSuccessful) {
            printMoviesTitle(response.body())
        }
    }

}*/

fun ejemploRetrofitCoroutines2() {

    val service = getMovieService()

    runBlocking {
        try {
            val moviesResponse = service.getNowPlayingMoviesAsync(page = 2)
            printMoviesTitle(moviesResponse)

        } catch ( ex : HttpException) {
            println(ex.message())
        }
    }
}