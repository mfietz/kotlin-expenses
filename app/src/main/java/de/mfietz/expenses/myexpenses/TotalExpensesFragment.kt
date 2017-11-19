package de.mfietz.expenses.myexpenses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx

class TotalExpensesFragment : Fragment() {

    val expenseRepository by lazy { ExpensesRepository(ctx) }
    lateinit var tvExpenses: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = UI {
        coordinatorLayout {
            padding = dip(16)
            tvExpenses = textView {
                textSize = 24f
            }
            floatingActionButton {
                id = R.id.add_expense
                onClick {
                    (activity as MainActivity).openAddExpenseFragment()
                }
                imageResource = R.drawable.ic_add_white_24dp
            }.lparams {
                gravity = Gravity.BOTTOM or Gravity.END
                margin = dip(8)
            }
        }
    }.view

    override fun onResume() {
        super.onResume()
        async(UI) {
            val expenses = expenseRepository.getAll()
            tvExpenses.text = expenses.groupBy { it.category }
                    .mapValues { it.value.sumBy { it.amount } }
                    .map { "• ${it.key}: ${it.value}" }
                    .joinToString("\n")
        }
    }

}
