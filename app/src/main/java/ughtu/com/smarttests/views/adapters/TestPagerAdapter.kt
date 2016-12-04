package ughtu.com.smarttests.views.adapters

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
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
        fragment.onConfirmButtonClickListener = View.OnClickListener {
            if(position == dataSource?.lastIndex) {
                done(fragment.activity)
            } else {
                val viewPager = fragment.view?.parent as ViewPager?
                viewPager?.setCurrentItem(position + 1, true)
            }
        }
        fragment.question = question
        return fragment
    }

    private fun done(context: Context?) {
        var correctAnswers = 0
        dataSource?.forEach { question ->
            question.answers?.forEach {
                if(it.isChecked) {
                    it.isCorrect
                }
            }
        }
        if(context == null) return
        val alert = AlertDialog.Builder(context)
        alert.setTitle("Результат!")
        alert.setMessage("Вы набрали баллов!")
        alert.setPositiveButton(android.R.string.ok) { a1, a2 ->
            a1.dismiss()
            (context as Activity).finish()
        }
        alert.show()
    }
}