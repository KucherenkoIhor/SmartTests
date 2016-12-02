package ughtu.com.smarttests.views.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import rx.android.schedulers.AndroidSchedulers
import ughtu.com.smarttests.R
import ughtu.com.smarttests.shared.ApiInterface
import ughtu.com.smarttests.shared.AppDelegate
import ughtu.com.smarttests.views.adapters.TestPagerAdapter
import javax.inject.Inject

class TestsActivity : AppCompatActivity() {

    companion object {
        val LECTURE_ID = "LECTURE_ID"
        fun newInstance(context: Context, lectureId: Long) : Intent {
            val intent = Intent(context, TestsActivity::class.java)
            intent.putExtra(LECTURE_ID, lectureId)
            return intent
        }
    }

    private val mProgressBar: ProgressBar by lazy {
        findViewById(R.id.progress_bar) as ProgressBar
    }
    private val mViewPager: ViewPager by lazy {
        findViewById(R.id.view_pager) as ViewPager
    }

    @Inject
    lateinit var mAPI: ApiInterface

    private val mAdapter: TestPagerAdapter by lazy {
        TestPagerAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tests)
        supportActionBar?.setTitle(R.string.tests)
        (application as AppDelegate).injector?.inject(this)
        mViewPager.adapter = mAdapter

        val lectureId = intent.getLongExtra(LECTURE_ID, 0)
        mAPI.getQuestions(lectureId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate { mProgressBar.visibility = View.GONE }
                .subscribe ({ mAdapter.dataSource = it }, { it.printStackTrace() })

    }
}
