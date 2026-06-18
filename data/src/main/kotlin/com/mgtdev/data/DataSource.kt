package com.mgtdev.data

import com.mgtdev.domain.Resource
import com.mgtdev.domain.UiText
import com.mgtdev.dto.ProductDTO
import javax.inject.Inject

class DataSource @Inject constructor(
    private val firebaseRepository: DatabaseRepository
) {
    suspend fun getAllOwnersSupport(): Resource<List<ProductDTO>> {
        return try {
            val owners = firebaseRepository.getSomeProducts()
            Resource.Success(owners)
        } catch (e: Exception) {
            Resource.Error(
                UiText.DynamicString(
                    e.message ?: "Error, prueba de nuevo más tarde"
                )
            )
        }
    }
}