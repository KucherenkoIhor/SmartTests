package ughtu.com.smarttests.views.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import ughtu.com.smarttests.model.Question
import ughtu.com.smarttests.views.TestFragment

/**
 * Created by igor on 02.12.16.
 */
class TestPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    var dataSource: List<Question>? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return dataSource?.size ?: 0
    }

    override fun getItem(position: Int): Fragment {
        val question = dataSource?.get(position)
        val fragment = TestFragment()
        fragment.question = question
        return fragment
    }
}