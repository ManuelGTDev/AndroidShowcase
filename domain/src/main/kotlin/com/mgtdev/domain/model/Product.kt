package com.mgtdev.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val imageUrl: String = "",
    val name: String = "",
    val brand: String = "",
    val description: String = "",
)