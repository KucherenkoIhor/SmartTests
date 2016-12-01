package ughtu.com.smarttests.views

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
import ughtu.com.smarttests.views.adapters.SubjectsAdapter
import javax.inject.Inject

class SubjectsActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context) : Intent {
            return Intent(context, SubjectsActivity::class.java)
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

    private val mAdapter = SubjectsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)
        (application as AppDelegate).injector?.inject(this)
        mRecyclerView.apply {
            setHasFixedSize(true)
            mRecyclerView.layoutManager = LinearLayoutManager(this@SubjectsActivity)
            adapter = mAdapter
        }

        mAPI.getSubjects()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate { mProgressBar.visibility = View.GONE }
                .subscribe( {mAdapter.dataSource = it},  { it.printStackTrace() })


    }
}
