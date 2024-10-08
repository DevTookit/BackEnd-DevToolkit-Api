package com.project.api.web.dto.request

import jakarta.validation.constraints.Email

data class UserCreateRequest(
    @field:Email
    val email: String,
    val name: String,
    val tags: List<String>?,
    val password: String,
    val job: String?,
)
