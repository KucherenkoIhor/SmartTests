package ughtu.com.smarttests.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ughtu.com.smarttests.R
import ughtu.com.smarttests.model.Answer

/**
 * Created by igor on 04.12.16.
 */
class AnswersAdapter: RecyclerView.Adapter<AnswersAdapter.AnswerViewHolder>() {

    var dataSource: List<Answer>? = null
        set(value) {
            field  = value
            notifyDataSetChanged()
        }

    var onItemClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AnswerViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.view_item, parent, false)
        return AnswerViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder?, position: Int) {
        holder?.answer = dataSource?.get(position)
        holder?.itemView?.setOnClickListener(onItemClickListener)
    }

    override fun getItemCount(): Int {
        return dataSource?.size ?: 0
    }

    public class AnswerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var answer: Answer? = null
            set(value) {
                textView.text = value?.text
                field = value
            }

        val textView: TextView by lazy {
            itemView?.findViewById(R.id.text_view) as TextView
        }

    }

}