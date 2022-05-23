package com.example.mymaster.presentations.scheduleSettingActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragmentActivity: FragmentActivity, private val num:Int) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return this.num
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentPage(0)
            else -> FragmentPage(1)
        }
    }
}