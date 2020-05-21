package com.example.sopt.network

import com.example.sopt.data.RequestSignup
import com.example.sopt.data.ResponseSignup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupRequestInterface {
    @POST("user/signup")
    fun requestSignup(@Body body : RequestSignup) : Call<ResponseSignup>
}