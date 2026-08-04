package com.example.freebank.core.data.remote.authenticator

import com.example.freebank.core.data.datastore.AuthPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class TokenManagerImpl @Inject constructor(
    private val authPreferences: AuthPreferences
) : TokenManager {

    override fun getAccessToken(): String? = runBlocking {
        authPreferences.token.first()
    }

    override fun getRefreshToken(): String? = runBlocking {
        authPreferences.refreshToken.first()
    }

    override fun saveTokens(accessTokens: String, refreshToken: String?) {
        runBlocking {
            authPreferences.saveToken(accessTokens)
            refreshToken?.let { authPreferences.saveRefreshToken(it) }
        }
    }

    override fun clearTokens() {
        runBlocking {
            authPreferences.clearToken()
            authPreferences.clearRefreshToken()
        }
    }
}