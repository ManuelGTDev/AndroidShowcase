package com.mgtdev.domain.usecase

import com.mgtdev.domain.RepositoryApi
import com.mgtdev.domain.Resource
import com.mgtdev.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetSomeDataUseCase @Inject constructor(
    private val repository: RepositoryApi,
) {
    suspend operator fun invoke(): Resource<List<Product>> =
        withContext(Dispatchers.IO) {
            repository.getSomeData()
        }
}