package de.mfietz.expenses.myexpenses

import java.util.*

data class Expense(
        val category: String,
        val amount: Int,
        val date: Date = Date()
)