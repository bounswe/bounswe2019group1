package com.project.khajit_app.activity

import android.app.Notification
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
import com.project.khajit_app.activity.ui.mailbox.MailboxFragment
import com.project.khajit_app.activity.ui.notifications.NotificationsFragment
import com.project.khajit_app.activity.ui.profile.UserProfile
import com.project.khajit_app.activity.ui.search.SearchFragment
import com.project.khajit_app.global.User

class HomeFeedPageActivity : AppCompatActivity() {

    private var content: FrameLayout? = null
    private var newFragment: Fragment? = null;//global variable



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_feed_page)
        setSupportActionBar(findViewById(R.id.home_top_menu_options_bar))
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
                changeFragment(fragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                val fragment = SearchFragment()
                changeFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_equipments -> {
                val fragment = EquipmentFragment()
                changeFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val fragment = NotificationsFragment()
                changeFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_messages -> {
                val fragment = MailboxFragment()
                changeFragment(fragment)
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
    private fun changeFragment(fragment: Fragment){
        val newFragment = fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content, newFragment,fragment.javaClass.simpleName)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_top, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.profile_top_menu_item -> {
            val profileFragment = UserProfile.Companion.newInstance()
            changeFragment(profileFragment)
            true
        }
        R.id.settings_top_menu_item -> {
            val profileFragment = UserProfile.Companion.newInstance()
            changeFragment(profileFragment)
            // do stuff
            true
        }
        R.id.logout_top_menu_item -> {

            User.token = ""
            User.id = 0
            User.username = ""
            User.email = ""
            User.first_name = ""
            User.last_name = ""
            // if the user is trader type info will be true otherwise user is basic and type info will be false
            User.type = false
            User.title = "No title"
            User.bio = "No bio"
            User.whereIamAsId = 0 //it may be unnecessary to keep
            startActivity(Intent(this, MainPageActivity::class.java))
            // do stuff
            true
        }
        else -> super.onOptionsItemSelected(item)

    }
}
