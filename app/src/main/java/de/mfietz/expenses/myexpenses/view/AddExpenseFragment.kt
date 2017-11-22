package de.mfietz.expenses.myexpenses.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.*
import android.widget.*
import de.mfietz.expenses.myexpenses.persistence.CategoriesRepository
import de.mfietz.expenses.myexpenses.Expense
import de.mfietz.expenses.myexpenses.R
import org.jetbrains.anko.customView
import org.jetbrains.anko.editText
import org.jetbrains.anko.listView
import org.jetbrains.anko.sdk25.coroutines.onItemClick
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.verticalLayout
import AppDatabase
import android.arch.persistence.room.Room




class AddExpenseFragment : Fragment() {

    val expensesDao by lazy {
        Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "my-expenses")
                .build()
                .expenseDao()
    }
    val categoriesRepositiry by lazy { CategoriesRepository(ctx.applicationContext) }
    lateinit var categories: MutableList<String>
    lateinit var listAdapter: BaseAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        categories = categoriesRepositiry.getAll().toMutableList()
        listAdapter = ArrayAdapter(ctx, android.R.layout.simple_list_item_1, categories)
        return UI {
            verticalLayout {
                listView {
                    id = R.id.category_list
                    adapter = listAdapter
                    onItemClick { _, _, position, _ ->
                        askAmount(categories[position])
                    }
                }
            }
        }.view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.categories_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_add_category -> {
            showAddCategoryDialog()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun showAddCategoryDialog() {
        val input = EditText(ctx)
        input.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
        AlertDialog.Builder(ctx)
                .setView(input)
                .setPositiveButton(android.R.string.ok, { dialogInterface, id ->
                    val newCategory = input.text.toString()
                    categoriesRepositiry.add(newCategory)
                    categories.add(newCategory)
                    listAdapter.notifyDataSetChanged()
                })
                .show()
    }

    private fun askAmount(category: String) {
        alert {
            title = "How much did you spend?"
            customView {
                val amount = editText()
                positiveButton(android.R.string.ok) {
                    val value = amount.text.toString().toInt()
                    expensesDao.save(Expense(category, value))
                    finish()
                }
            }
        }.show()
    }

    private fun finish() {
        activity.supportFragmentManager.popBackStack()
    }

}