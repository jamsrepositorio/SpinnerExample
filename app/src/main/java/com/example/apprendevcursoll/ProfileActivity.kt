package com.example.apprendevcursoll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

class ProfileActivity : AppCompatActivity() {
    private val profileFragment = ProfileFragment()
    private val mainFragment = MainFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        loadFragment(profileFragment)
        replaceFragment(mainFragment)
        removeFragment(mainFragment)

    }

    fun loadFragment(fragment: Fragment) {
        Log.d(ProfileActivity::class.java.simpleName,"Fragment 1")
        supportFragmentManager.beginTransaction()
            .add(
            R.id.frameContainer,
            fragment,
            fragment::class.java.simpleName
        )
            .commit()
    }

    fun replaceFragment(fragment: Fragment) {
        Log.d(ProfileActivity::class.java.simpleName,"Fragment 2")
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frameContainer,
                fragment,
                fragment::class.java.simpleName
            )
            .addToBackStack(null)
            .commit()
    }

    fun removeFragment(fragment: Fragment)
    {
        Log.d(ProfileActivity::class.java.simpleName,"Fragment 3")
        supportFragmentManager.beginTransaction().remove(fragment).commit()
    }
}