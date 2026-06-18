package com.mgtdev.dto

import com.mgtdev.domain.model.Product

data class ProductDTO(
    val id: String? = null,
    val imageUrl: String? = null,
    val name: String? = null,
    val brand: String? = null,
    val description: String? = null
) {
    // Función para mapear a la entidad del dominio
    fun toDomain() = Product(
        id = id ?: "",
        imageUrl = imageUrl ?: "",
        name = name ?: "",
        brand = brand ?: "",
        description = description ?: ""
    )
}