package de.mfietz.expenses.myexpenses.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import de.mfietz.expenses.myexpenses.Expense

@Database(entities = arrayOf(Expense::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object{
        const val databaseName = "expenses"

        private lateinit var context: Context

        private val database: AppDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
                    .build()
        }

        fun expensesDao(context: Context): ExpenseDao {
            Companion.context = context.applicationContext
            return database.expenseDao()
        }

    }

}