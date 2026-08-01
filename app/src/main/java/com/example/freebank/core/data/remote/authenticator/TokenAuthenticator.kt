package com.example.freebank.core.data.remote.authenticator

import com.example.freebank.core.data.remote.api.ArcaBankAuthApi
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider

class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val apiProvider: Provider<ArcaBankAuthApi>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = tokenManager.getAccessToken()

        synchronized(this) {
            val updatedToken = tokenManager.getAccessToken()

            if (currentToken != updatedToken) {
                return buildRequest(response.request, updatedToken)
            }

            val refreshToken = tokenManager.getRefreshToken() ?: return null

            return runBlocking {
                try {
                    val refreshResponse = apiProvider.get().refreshToken(
                        mapOf("refresh_token" to refreshToken)
                    )

                    if (refreshResponse.isSuccessful && refreshResponse.body() != null) {
                        val newTokens = refreshResponse.body()!!
                        tokenManager.saveTokens(newTokens.accessToken, newTokens.refreshToken)
                        buildRequest(response.request, newTokens.accessToken)
                    } else {
                        tokenManager.clearTokens()
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }
        }
    }

    private fun buildRequest(request: Request, accessToken: String?): Request {
        return request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
    }
}