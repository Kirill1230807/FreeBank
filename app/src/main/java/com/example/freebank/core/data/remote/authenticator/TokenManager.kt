package com.example.freebank.core.data.remote.authenticator

interface TokenManager {
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun saveTokens(accessTokens: String, refreshToken: String?)
    fun clearTokens()
}