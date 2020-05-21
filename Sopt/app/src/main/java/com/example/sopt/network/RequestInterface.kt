package com.example.sopt.network

import com.example.sopt.data.RequestLogin
import com.example.sopt.data.ResponseLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface RequestInterface{
    @POST("/user/signin")
    fun requestLogin(@Body body : RequestLogin) : Call<ResponseLogin>
}