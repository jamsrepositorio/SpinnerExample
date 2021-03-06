package com.example.apprendevcursoll.tablayoutsample.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.apprendevcursoll.R
import com.example.apprendevcursoll.spinnersample.ui.spinner.SpinnerFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {

        0 -> {

            PlaceholderFragment()
        }
        else -> {
            SpinnerFragment()


        }


    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {

        return 2
    }
}