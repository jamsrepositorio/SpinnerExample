package com.example.apprendevcursoll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apprendevcursoll.dialog.ColorDialogFragment
import com.example.apprendevcursoll.dialog.MulticolorsDialogFragment
import com.example.apprendevcursoll.dialog.QuestionDialogFragment

class Dialogactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogactivity)
        /*val questionDialogFragment = QuestionDialogFragment()
        questionDialogFragment.show(supportFragmentManager, QuestionDialogFragment::class.java.simpleName)*/

        /*val colorsDialogFragment = ColorDialogFragment()
        colorsDialogFragment.show(
            supportFragmentManager,
            ColorDialogFragment::class.java.simpleName
        )*/

        val multicolorsDialogFragment = MulticolorsDialogFragment()
        multicolorsDialogFragment.show(
            supportFragmentManager,
            MulticolorsDialogFragment::class.java.simpleName
        )
    }
}