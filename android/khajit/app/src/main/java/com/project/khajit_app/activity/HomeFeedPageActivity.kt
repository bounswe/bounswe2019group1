package com.project.khajit_app.activity

import android.app.Notification
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.project.khajit_app.R
import com.project.khajit_app.activity.ui.equipment.EquipmentFragment
import com.project.khajit_app.activity.ui.home.HomeFragment
import com.project.khajit_app.activity.ui.mailbox.MailboxFragment
import com.project.khajit_app.activity.ui.notifications.NotificationsFragment
import com.project.khajit_app.activity.ui.search.SearchFragment

class HomeFeedPageActivity : AppCompatActivity() {

    private var content: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_feed_page)

        content = findViewById(R.id.content)
        val navigation = findViewById<BottomNavigationView>(R.id.nav_view)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = HomeFragment.Companion.newInstance()
        addFragment(fragment)
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                val fragment = HomeFragment.Companion.newInstance()
                addFragment(fragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                val fragment = SearchFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_equipments -> {
                val fragment = EquipmentFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val fragment = NotificationsFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_messages -> {
                val fragment = MailboxFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_top, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.profile_top_menu_item -> {
            // do stuff
            true
        }
        R.id.settings_top_menu_item -> {
            // do stuff
            true
        }
        R.id.logout_top_menu_item -> {
            // do stuff
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
