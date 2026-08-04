package com.example.freebank.core.domain.error

sealed interface DataError {
    sealed interface Network : DataError {
        data object NO_INTERNET : Network
        data object SERVER_ERROR : Network
        data object UNAUTHORIZED : Network
        data object UNKNOWN : Network
    }
}

sealed interface MyResult<out D, out E: DataError> {
    data class Success<out D>(val data: D): MyResult<D, Nothing>
    data class Error<out E : DataError>(val error: E): MyResult<Nothing, E>
}