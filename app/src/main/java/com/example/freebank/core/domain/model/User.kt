package com.example.freebank.core.domain.model

import com.example.freebank.core.domain.model.enums.Status

data class User(
    val id: String,
    val passportId: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val status: Status = Status.ACTIVE
)
