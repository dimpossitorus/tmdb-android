package com.dimpossitorus.android.tmdb.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dimpossitorus.android.tmdb.R

open class BaseActivity : AppCompatActivity() {

    fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.addToBackStack(fragment::class.java.canonicalName)
        fragmentTransaction.commit()
    }

    fun replaceFragmentWithoutBackstack(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }

    fun addFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, fragment)
        fragmentTransaction.addToBackStack(fragment::class.java.canonicalName)
        fragmentTransaction.commit()
    }
}