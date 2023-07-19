package dev.arganaphang.manx.repository

import dev.arganaphang.manx.data.Transactions
import dev.arganaphang.manx.entity.TransactionEnum
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun create(title: String, amount: UInt, type: TransactionEnum.TransactionType)
    suspend fun findOneByID(id: Long): Transactions?
    fun findAll(): Flow<List<Transactions>>
    fun findByType(type: TransactionEnum.TransactionType): Flow<List<Transactions>>
}