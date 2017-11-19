package de.mfietz.expenses.myexpenses

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.FrameLayout
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        navigateTo(TotalExpensesFragment(), addToBackStack = false)
    }

    private fun navigateTo(fragment: Fragment, addToBackStack: Boolean = true) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            if(addToBackStack) addToBackStack(null)
            commit()
        }
    }

    fun openAddExpenseFragment() {
        navigateTo(AddExpenseFragment())
    }

}

class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        frameLayout {
            id = R.id.fragment_container
            layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)
        }
    }

}