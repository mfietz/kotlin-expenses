package de.mfietz.expenses.myexpenses.persistence

import android.content.Context
import com.google.gson.Gson
import org.jetbrains.anko.defaultSharedPreferences

class CategoriesRepository(ctx: Context) {

    val preferences = ctx.defaultSharedPreferences
    val gson = Gson()

    private companion object {
        val PREFERENCE_KEY = "categories"
    }

    fun add(category: String) {
        val expenses = getAll().toMutableSet()
        expenses.add(category)
        preferences.edit()
                .putStringSet(PREFERENCE_KEY, expenses)
                .apply()
    }

    fun getAll(): Set<String> {
        return preferences.getStringSet(PREFERENCE_KEY, emptySet())
    }

}