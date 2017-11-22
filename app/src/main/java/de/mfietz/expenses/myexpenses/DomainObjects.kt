package de.mfietz.expenses.myexpenses

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Expense(
        val category: String,
        val amount: Int,
        val date: Date = Date()
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}