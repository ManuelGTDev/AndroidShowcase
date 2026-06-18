package com.mgtdev.domain

import com.mgtdev.domain.model.Product
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface RepositoryApi {

    suspend fun getSomeData(): Resource<List<Product>>
}