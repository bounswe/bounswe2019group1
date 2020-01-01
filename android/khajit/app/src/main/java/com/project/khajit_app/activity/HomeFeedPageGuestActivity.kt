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
import com.project.khajit_app.activity.ui.article.ListArticleFragment
import com.project.khajit_app.activity.ui.equipment.EquipmentFragment
import com.project.khajit_app.activity.ui.event.ListEventFragment
import com.project.khajit_app.activity.ui.home.HomeFragment
import com.project.khajit_app.activity.ui.home.HomeFragmentGuest
import com.project.khajit_app.activity.ui.mailbox.MailboxFragment
import com.project.khajit_app.activity.ui.notifications.NotificationsFragment
import com.project.khajit_app.activity.ui.search.SearchFragment
import interfaces.fragmentOperationsInterface

class HomeFeedPageGuestActivity : AppCompatActivity() , fragmentOperationsInterface {

    private var content: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_feed_page_guest)

        setSupportActionBar(findViewById(R.id.home_top_menu_guest_options_bar))

        content = findViewById(R.id.content_guest)
        val navigation = findViewById<BottomNavigationView>(R.id.nav_view_guest)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //val fragment = HomeFragmentGuest.Companion.newInstance()
        val fragment = ListArticleFragment.Companion.newInstance(1,0,1,0,-1)
        /*val user = UserAllInfo(        listOf("1","2"), 9,
        "cer3d@hotmail.com",

        "cer3",
        "dardi",
             "cer3d@hotmail.com",
             "locationInfo",
         "905086395214",
        "0",
         "",
         "I am para babasi.",
         "para baba",
"2019-11-24T14:31:15.031985Z")*/
        //val article = GeneralArticleModel(1," DENEME TITLE", "ASLJFGHKLJFG",user,true,"2 mayıs")
        //val fragment = displayArticleFragment.Companion.newInstance(article,1,0,1,0,-1)
        fragmentTransaction(
            supportFragmentManager,
            fragment,
            content!!.id,
            true,
            true,
            false
        )

    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home_guest -> {

                //val fragment = HomeFragmentGuest.Companion.newInstance()
                val fragment = ListArticleFragment.Companion.newInstance(1,0,1,0,-1)
                /*val user = UserAllInfo(        listOf("1","2"), 9,
                "cer3d@hotmail.com",

                "cer3",
                "dardi",
                     "cer3d@hotmail.com",
                     "locationInfo",
                 "905086395214",
                "0",
                 "",
                 "I am para babasi.",
                 "para baba",
       "2019-11-24T14:31:15.031985Z")*/
                //val article = GeneralArticleModel(1," DENEME TITLE", "ASLJFGHKLJFG",user,true,"2 mayıs")
                //val fragment = displayArticleFragment.Companion.newInstance(article,1,0,1,0,-1)
                fragmentTransaction(
                    supportFragmentManager,
                    fragment,
                    content!!.id,
                    true,
                    true,
                    false
                )


                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search_guest -> {
                val fragment = ListEventFragment()
                fragmentTransaction(
                    supportFragmentManager,
                    fragment,
                    content!!.id,
                    true,
                    true,
                    false
                )
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_equipments_guest -> {
                val fragment = EquipmentFragment.newInstance(1)
                fragmentTransaction(
                    supportFragmentManager,
                    fragment,
                    content!!.id,
                    true,
                    true,
                    false
                )
                return@OnNavigationItemSelectedListener true
            }

        }
        false
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
