package com.example.apprendevcursoll.spinnersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apprendevcursoll.R
import com.example.apprendevcursoll.spinnersample.ui.spinner.SpinnerFragment

class SpinnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spinner_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SpinnerFragment())
                .commitNow()
        }
    }
}