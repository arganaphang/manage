package dev.arganaphang.manx.di

import android.app.Application
import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.arganaphang.Database
import dev.arganaphang.manx.data.Transactions
import dev.arganaphang.manx.repository.TransactionRepository
import dev.arganaphang.manx.repository.impl.TransactionRepositoryImpl
import java.util.Date
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoRepository(app: Application): TransactionRepository {
        val driver = AndroidSqliteDriver(
            schema = Database.Schema,
            context = app,
            name = "transactions.db"
        )
        val createdAtAdapter = object : ColumnAdapter<Date, Long> {
            override fun decode(databaseValue: Long): Date {
                return Date(databaseValue)
            }
            override fun encode(value: Date): Long {
                return value.time
            }
        }
        return TransactionRepositoryImpl(Database.invoke(driver = driver, transactionsAdapter = Transactions.Adapter(
            typeAdapter = EnumColumnAdapter(),
            createdAtAdapter = createdAtAdapter
        )))
    }
}