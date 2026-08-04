package com.example.freebank.core.domain.repository

import com.example.freebank.core.domain.error.DataError
import com.example.freebank.core.domain.error.MyResult

interface AuthRepository {
    suspend fun login(email: String, password: String): MyResult<Unit, DataError.Network>
    suspend fun register()
    suspend fun logout()
}