package de.mfietz.expenses.myexpenses.persistence

import android.arch.persistence.room.TypeConverter
import java.util.*


class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = Date(value ?: 0)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

}
