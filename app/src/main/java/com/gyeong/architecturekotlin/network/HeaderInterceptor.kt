package com.gyeong.architecturekotlin.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

//        if existing token
//        request.newBuilder()
//            .header("Authorization", token)
//            .build()

        return chain.proceed(request)
    }
}