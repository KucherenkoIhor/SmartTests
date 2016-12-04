package ughtu.com.smarttests.views


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test.*
import rx.android.schedulers.AndroidSchedulers
import ughtu.com.smarttests.R
import ughtu.com.smarttests.model.Question
import ughtu.com.smarttests.shared.ApiInterface
import ughtu.com.smarttests.shared.AppDelegate
import ughtu.com.smarttests.views.adapters.AnswersAdapter
import javax.inject.Inject

/**
 * Created by igor on 02.12.16.
 */
class TestFragment : Fragment() {

    var onConfirmButtonClickListener: View.OnClickListener? = null

    var question: Question? = null

    @Inject
    lateinit var mAPI: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity.application as AppDelegate).injector?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionTextView.text = question?.text
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        val adapter = AnswersAdapter()
        recyclerView.adapter = adapter
        adapter.onItemClickListener = View.OnClickListener { view ->
            val holder = recyclerView.findContainingViewHolder(view) as AnswersAdapter.AnswerViewHolder
            holder.answer?.isChecked = holder.answer?.isChecked?.not() ?: false
            if(holder.answer?.isChecked ?: false) {
                (holder.itemView as CardView?)?.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimary))
            } else {
                (holder.itemView as CardView?)?.setCardBackgroundColor(Color.TRANSPARENT)
            }
        }

        mAPI.getAnswers(question?.id ?: 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ adapter.dataSource = it}, {it.printStackTrace()})

        confirmButton.setOnClickListener(onConfirmButtonClickListener)

    }
}