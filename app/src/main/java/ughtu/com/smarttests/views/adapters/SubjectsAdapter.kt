package ughtu.com.smarttests.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ughtu.com.smarttests.R
import ughtu.com.smarttests.model.Subject

/**
 * Created by igor on 01.12.16.
 */
class SubjectsAdapter : RecyclerView.Adapter<SubjectsAdapter.SubjectsVieHolder>() {

    var dataSource: List<Subject>? = null
    set(value) {
        field  = value
        notifyDataSetChanged()
    }

    var onItemClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SubjectsVieHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.view_item, parent, false)
        return SubjectsVieHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectsVieHolder?, position: Int) {
        holder?.subject = dataSource?.get(position)
        holder?.itemView?.setOnClickListener(onItemClickListener)
    }

    override fun getItemCount(): Int {
        return dataSource?.size ?: 0
    }

    public class SubjectsVieHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var subject: Subject? = null
        set(value) {
            textView.text = value?.name
            field = value
        }

        val textView: TextView by lazy {
            itemView?.findViewById(R.id.text_view) as TextView
        }

    }

}