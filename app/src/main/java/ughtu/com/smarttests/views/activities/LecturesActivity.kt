package ughtu.com.smarttests.views.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import rx.android.schedulers.AndroidSchedulers
import ughtu.com.smarttests.R
import ughtu.com.smarttests.shared.ApiInterface
import ughtu.com.smarttests.shared.AppDelegate
import ughtu.com.smarttests.views.adapters.LecturesAdapter
import javax.inject.Inject

class LecturesActivity : AppCompatActivity() {

    companion object {
        val SUBJECT_ID = "SUBJECT_ID"
        fun newInstance(context: Context, subjectId: Long) : Intent {
            val intent = Intent(context, LecturesActivity::class.java)
            intent.putExtra(SUBJECT_ID, subjectId)
            return intent
        }
    }

    private val mProgressBar: ProgressBar by lazy {
        findViewById(R.id.subjects_progress) as ProgressBar
    }
    private val mRecyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view) as RecyclerView
    }

    @Inject
    lateinit var mAPI: ApiInterface

    private val mAdapter = LecturesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lectures)
        supportActionBar?.setTitle(R.string.lectures)
        (application as AppDelegate).injector?.inject(this)
        mRecyclerView.apply {
            setHasFixedSize(true)
            mRecyclerView.layoutManager = LinearLayoutManager(this@LecturesActivity)
            adapter = mAdapter
        }

        mAdapter.onItemClickListener = View.OnClickListener { view ->
            val holder = mRecyclerView.findContainingViewHolder(view) as LecturesAdapter.LectureViewHolder
            val lectureId = holder.lecture?.id ?: return@OnClickListener
            AppDelegate.lectureId = lectureId
            startActivity(TestsActivity.newInstance(this, lectureId))
        }

        val subjectId = intent.getLongExtra(SUBJECT_ID, 0)

        mAPI.getLectures(subjectId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate { mProgressBar.visibility = View.GONE }
                .subscribe( {mAdapter.dataSource = it},  { it.printStackTrace() })

    }
}
