package com.bellman.task.presentation.features.home

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bellman.task.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import com.hitomi.cmlibrary.OnMenuStatusChangeListener
import com.hitomi.cmlibrary.OnMenuSelectedListener


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    private fun init() {
        bottomBar.itemIconTintList = null
        bottomBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomBar.selectedItemId = R.id.home
    }


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    setFragment(HomeFragment(), "HOME_FRAGMENT")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.search -> {

                    setFragment(HomeFragment(), "SEARCH_FRAGMENT")
                    return@OnNavigationItemSelectedListener true
                }

                R.id.notification -> {

                    setFragment(HomeFragment(), "NOTIFICATION_FRAGMENT")
                    return@OnNavigationItemSelectedListener true
                }

                R.id.profile -> {

                    setFragment(HomeFragment(), "PROFILE_FRAGMENT")
                    return@OnNavigationItemSelectedListener true
                }
            }

            true
        }


    private fun setFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment, tag)
        transaction.commit()
    }
}
