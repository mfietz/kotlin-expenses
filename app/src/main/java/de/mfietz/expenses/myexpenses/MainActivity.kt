package de.mfietz.expenses.myexpenses

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TotalExpensesFragment())
                .addToBackStack(null)
                .commit()
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