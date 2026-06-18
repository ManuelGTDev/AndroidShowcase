package com.mgtdev.data

import com.mgtdev.domain.RepositoryApi
import com.mgtdev.domain.Resource
import com.mgtdev.domain.UiText
import com.mgtdev.domain.model.Product
import com.mgtdev.dto.ProductDTO
import javax.inject.Inject

class RepositoryApiImpl @Inject constructor(
    private val localSource: DataSource
) : RepositoryApi {

    override suspend fun getSomeData(): Resource<List<Product>> {
        return when (val result = localSource.getAllOwnersSupport()) {
            is Resource.Success -> {
                Resource.Success(
                    data = result.data.orEmpty().map(ProductDTO::toDomain)
                )
            }

            is Resource.Error -> {
                Resource.Error(
                    message = result.message
                        ?: UiText.DynamicString("Error, prueba de nuevo más tarde"),
                    data = result.data?.map(ProductDTO::toDomain)
                )
            }

            is Resource.Loading -> {
                Resource.Loading(
                    data = result.data?.map(ProductDTO::toDomain)
                )
            }
        }
    }
}