package com.example.freebank.core.data.repositoryImpl

import com.example.freebank.core.data.datastore.AuthPreferences
import com.example.freebank.core.domain.error.DataError
import com.example.freebank.core.domain.error.MyResult
import com.example.freebank.core.data.remote.api.ArcaBankAuthApi
import com.example.freebank.core.data.remote.api.dto.login.LoginRequestDto
import com.example.freebank.core.domain.repository.AuthRepository
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authPreferences: AuthPreferences,
    private val arcaBankAuthApi: ArcaBankAuthApi
) : AuthRepository {
    override suspend fun login(email: String, password: String): MyResult<Unit, DataError.Network> {
        val response = try {
            arcaBankAuthApi.loginUser(LoginRequestDto(email = email, password = password))
        } catch (e: IOException) {
            return MyResult.Error(DataError.Network.NO_INTERNET)
        }

        return when (response.code()) {
            200 -> {
                val tokens = response.body()
                if (tokens != null) {
                    authPreferences.saveToken(tokens.accessToken)
                    authPreferences.saveRefreshToken(tokens.refreshToken)
                    MyResult.Success(Unit)
                } else {
                    MyResult.Error(DataError.Network.UNKNOWN)
                }
            }

            401 -> {
                MyResult.Error(DataError.Network.UNAUTHORIZED)
            }

            in 500..599 -> MyResult.Error(DataError.Network.SERVER_ERROR)
            else -> MyResult.Error(DataError.Network.UNKNOWN)
        }
    }

    override suspend fun register() {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        authPreferences.clearToken()
        authPreferences.clearRefreshToken()
    }
}