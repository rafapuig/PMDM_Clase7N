package es.rafapuig.movieapp.data.network

import okhttp3.Interceptor
import okhttp3.Response

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