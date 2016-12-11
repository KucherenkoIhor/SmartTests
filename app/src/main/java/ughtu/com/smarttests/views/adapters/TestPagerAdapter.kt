package ughtu.com.smarttests.views.adapters

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import rx.android.schedulers.AndroidSchedulers
import ughtu.com.smarttests.model.Question
import ughtu.com.smarttests.shared.ApiInterface
import ughtu.com.smarttests.shared.AppDelegate
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

    var api: ApiInterface? = null

    var correctAnswers = 0

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
                if(fragment.adapter.isCorrect) {
                    correctAnswers ++
                }
                viewPager?.setCurrentItem(position + 1, true)
            }
        }
        fragment.question = question
        return fragment
    }

    private fun done(context: Context?) {

        val result = (correctAnswers.toDouble() / dataSource!!.size.toDouble()) * 100
        try {
            api?.
                    addResult(AppDelegate.groupName!!, result.toInt(), AppDelegate.lectureId!!)?.
                    observeOn(AndroidSchedulers.mainThread())?.
                    subscribe({}, { it.printStackTrace() })
        } catch (e : Exception) {
            e.printStackTrace()
        }
        if(context == null) return
        val alert = AlertDialog.Builder(context)
        alert.setTitle("Результат!")
        alert.setMessage("Вы набрали баллов $result!")
        alert.setPositiveButton(android.R.string.ok) { a1, a2 ->
            a1.dismiss()
            (context as Activity).finish()
        }
        alert.show()
    }
}