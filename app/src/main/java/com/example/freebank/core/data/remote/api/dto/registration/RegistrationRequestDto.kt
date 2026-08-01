package com.example.freebank.core.data.remote.api.dto.registration

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequestDto(
    @SerialName("passport_id")
    val passportId: String,
    @SerialName("email")
    val email: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("phoneNumber")
    val phoneNUmber: String,
    @SerialName("password")
    val password: String
)