package de.mfietz.expenses.myexpenses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.UI

class TotalExpensesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            coordinatorLayout {
                padding = dip(16)
                textView {
                    text = "Total Expenses:\nNothing yet"
                    textSize = 18f
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
    }

}
