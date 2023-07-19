package dev.arganaphang.manx.repository.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import dev.arganaphang.Database
import dev.arganaphang.manx.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date
import dev.arganaphang.manx.data.Transactions
import dev.arganaphang.manx.entity.TransactionEnum
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(
    private val database: Database
) : TransactionRepository {
    override suspend fun create(title: String, amount: UInt, type: TransactionEnum.TransactionType) {
        withContext(Dispatchers.IO) {
            val now = Date()
            database.transactionQueries.insert(id = now.time, title = title, amount = amount.toLong(), type = type, createdAt = now)
        }
    }

    override suspend fun findOneByID(id: Long): Transactions? {
        return database.transactionQueries.findOneByID(id).executeAsOneOrNull()
    }

    override fun findAll(): Flow<List<Transactions>> {
        return database.transactionQueries.findAll().asFlow().mapToList(Dispatchers.IO)
    }

    override fun findByType(type: TransactionEnum.TransactionType): Flow<List<Transactions>> {
        return database.transactionQueries.findByType(type).asFlow().mapToList(Dispatchers.IO)
    }
}