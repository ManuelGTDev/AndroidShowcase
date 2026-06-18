package com.mgtdev.data

import com.mgtdev.dto.ProductDTO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepository @Inject constructor(

) {
    private val products = listOf(
        ProductDTO(
            id = "1",
            imageUrl = "https://example.com/product-1.jpg",
            name = "Pixel 9",
            brand = "Google",
            description = "Smartphone Android de ejemplo",
        ),
        ProductDTO(
            id = "2",
            imageUrl = "https://example.com/product-2.jpg",
            name = "Galaxy S25",
            brand = "Samsung",
            description = "Producto local para mostrar la arquitectura",
        ),
        ProductDTO(
            id = "3",
            imageUrl = "https://example.com/product-3.jpg",
            name = "Xperia 1",
            brand = "Sony",
            description = "Tercer producto de la lista local",
        ),
    )

    suspend fun getSomeProducts(): List<ProductDTO> =
        withTimeout(10_000L) {
            // Simulación de acceso a base de datos.
            delay(500L)
            products
        }
}