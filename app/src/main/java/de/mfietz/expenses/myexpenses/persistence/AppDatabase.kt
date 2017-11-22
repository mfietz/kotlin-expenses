import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import de.mfietz.expenses.myexpenses.Expense
import de.mfietz.expenses.myexpenses.persistence.ExpenseDao

@Database(entities = arrayOf(Expense::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}