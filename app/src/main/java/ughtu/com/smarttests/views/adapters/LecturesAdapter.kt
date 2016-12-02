package ughtu.com.smarttests.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ughtu.com.smarttests.R
import ughtu.com.smarttests.model.Lecture

/**
 * Created by igor on 02.12.16.
 */
class LecturesAdapter : RecyclerView.Adapter<LecturesAdapter.LectureViewHolder>() {

    var dataSource: List<Lecture>? = null
        set(value) {
            field  = value
            notifyDataSetChanged()
        }

    var onItemClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LectureViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.view_item, parent, false)
        return LectureViewHolder(view)
    }

    override fun onBindViewHolder(holder: LectureViewHolder?, position: Int) {
        holder?.lecture = dataSource?.get(position)
        holder?.itemView?.setOnClickListener(onItemClickListener)
    }

    override fun getItemCount(): Int {
        return dataSource?.size ?: 0
    }

    public class LectureViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var lecture: Lecture? = null
            set(value) {
                textView.text = value?.name
                field = value
            }

        val textView: TextView by lazy {
            itemView?.findViewById(R.id.text_view) as TextView
        }

    }

}