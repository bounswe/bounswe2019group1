package com.project.khajit_app.activity

import android.app.Notification
import android.content.Context
import android.content.Intent
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
import com.project.khajit_app.activity.ui.home.HomeFragmentGuest
import com.project.khajit_app.activity.ui.mailbox.MailboxFragment
import com.project.khajit_app.activity.ui.notifications.NotificationsFragment
import com.project.khajit_app.activity.ui.search.SearchFragment

class HomeFeedPageGuestActivity : AppCompatActivity() {

    private var content: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_feed_page_guest)

        setSupportActionBar(findViewById(R.id.home_top_menu_guest_options_bar))

        content = findViewById(R.id.content_guest)
        val navigation = findViewById<BottomNavigationView>(R.id.nav_view_guest)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = HomeFragmentGuest.Companion.newInstance()
        addFragment(fragment)
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home_guest -> {

                val fragment = HomeFragmentGuest.Companion.newInstance()
                addFragment(fragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search_guest -> {
                val fragment = SearchFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_equipments_guest -> {
                val fragment = NotificationsFragment()
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
            .replace(R.id.content_guest, fragment, fragment.javaClass.simpleName)
            .commit()

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_top_guest, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.register_direct_guest -> {
            startActivity(Intent(this, SignUpPageActivity::class.java))
            true
        }
        R.id.login_direct_guest -> {
            startActivity(Intent(this, LoginPageActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)

    }
    companion object {
        fun getLaunchIntent(from : Context) = Intent(from, MainPageActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}
