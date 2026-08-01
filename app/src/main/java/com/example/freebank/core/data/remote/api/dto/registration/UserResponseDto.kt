package com.example.freebank.core.data.remote.api.dto.registration

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    @SerialName("id")
    val id: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("email")
    val email: String,
    @SerialName("passportId")
    val passportId: String,
    @SerialName("phoneNumber")
    val phoneNumber: String
)
