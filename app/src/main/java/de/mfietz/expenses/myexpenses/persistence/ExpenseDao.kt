package de.mfietz.expenses.myexpenses.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import de.mfietz.expenses.myexpenses.Expense

@Dao
interface ExpenseDao {

    @Query("select * from Expense")
    fun getAllExpenses(): List<Expense>

    @Insert
    fun save(expense: Expense)

}