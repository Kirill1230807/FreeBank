package com.example.freebank.core.data.remote.api

import com.example.freebank.core.data.remote.api.dto.login.LoginRequestDto
import com.example.freebank.core.data.remote.api.dto.login.TokenResponseDto
import com.example.freebank.core.data.remote.api.dto.registration.RegistrationRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ArcaBankAuthApi {
    @POST("api/v1/auth/public/register")
    suspend fun registerUser(
        @Body requestDto: RegistrationRequestDto): Response<String>

    @POST("api/v1/auth/public/login")
    suspend fun loginUser(
        @Body requestDto: LoginRequestDto): Response<TokenResponseDto>

    @POST("api/v1/auth/public/refresh")
    suspend fun refreshToken(
        @Body body: Map<String, String>
    ): Response<TokenResponseDto>
}