package de.mfietz.expenses.myexpenses

import android.content.Context
import com.google.gson.Gson
import org.jetbrains.anko.defaultSharedPreferences

class ExpensesRepository(ctx: Context) {

    val preferences = ctx.defaultSharedPreferences
    val gson = Gson()

    private companion object {
        val PREFERENCE_KEY = "expenses"
    }

    fun add(expense: Expense) {
        val expenses = getAll().toMutableSet()
        expenses.add(expense)
        preferences.edit()
                .putStringSet(PREFERENCE_KEY, expenses.map { gson.toJson(it) }.toSet())
                .apply()
    }

    fun getAll(): List<Expense> {
        return preferences.getStringSet(PREFERENCE_KEY, emptySet())
                .map { gson.fromJson(it, Expense::class.java) }
    }

}