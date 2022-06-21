package com.example.onyourway

import retrofit2.http.POST
import com.example.onyourway.LoginRequest
import com.example.onyourway.LoginResponse
import com.example.onyourway.RegisterRequest
import com.example.onyourway.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body

interface RiderInterface {
    @POST("/auth/login")
    fun loginUser(@Body loginRequest: LoginRequest?): Call<LoginResponse?>?

    @POST("/auth/register")
    fun registerUser(@Body registerRequest: RegisterRequest?): Call<RegisterResponse?>?
}