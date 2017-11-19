package de.mfietz.expenses.myexpenses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import org.jetbrains.anko.customView
import org.jetbrains.anko.editText
import org.jetbrains.anko.listView
import org.jetbrains.anko.sdk25.coroutines.onItemClick
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.verticalLayout

class AddExpenseFragment : Fragment() {

    val expenseRepository by lazy { ExpensesRepository(ctx.applicationContext) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val categories = arrayOf("Food", "Entertainment")
        val listAdapter = ArrayAdapter(ctx, android.R.layout.simple_list_item_1, categories)
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

    private fun askAmount(category: String) {
        alert {
            title = "How much did you spend?"
            customView {
                val amount = editText()
                positiveButton(android.R.string.ok) {
                    val value = amount.text.toString().toInt()
                    expenseRepository.add(Expense(category, value))
                    finish()
                }
            }
        }.show()
    }

    private fun finish() {
        activity.supportFragmentManager.popBackStack()
    }

}