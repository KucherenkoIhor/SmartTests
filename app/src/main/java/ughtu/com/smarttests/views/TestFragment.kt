package ughtu.com.smarttests.views


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ughtu.com.smarttests.R
import ughtu.com.smarttests.model.Question

/**
 * Created by igor on 02.12.16.
 */
class TestFragment : Fragment() {

    var question: Question? = null

    private val mQuestionTextView: TextView by lazy {
        view?.findViewById(R.id.text_view) as TextView
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mQuestionTextView.text = question?.text + "листай вправо"
    }
}