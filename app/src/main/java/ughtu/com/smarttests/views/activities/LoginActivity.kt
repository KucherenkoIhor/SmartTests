package ughtu.com.smarttests.views.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import rx.android.schedulers.AndroidSchedulers
import ughtu.com.smarttests.R
import ughtu.com.smarttests.model.Group
import ughtu.com.smarttests.shared.ApiInterface
import ughtu.com.smarttests.shared.AppDelegate
import java.util.*
import javax.inject.Inject



/**
 * A login screen.
 */
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var mAPI: ApiInterface

    private val mSpinner: Spinner by lazy {
        findViewById(R.id.login_spinner) as Spinner
    }
    private val mProgressBar: ProgressBar by lazy {
        findViewById(R.id.login_progress) as ProgressBar
    }
    private val mSignIn: Button by lazy {
        findViewById(R.id.sign_in_button) as Button
    }
    private val mPassEditText: EditText by lazy {
        findViewById(R.id.password) as EditText
    }

    private val mGroups = ArrayList<Group>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.setTitle(R.string.action_sign_in)
        inject()
        loadGroups()
        mSignIn.setOnClickListener {
            val pass = mPassEditText.text.toString()
            val selectedGroup = mGroups[mSpinner.selectedItemPosition]
            if(pass == selectedGroup.pass) {
                startActivity(SubjectsActivity.newInstance(this))
            } else {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadGroups() {
        mAPI.getGroups()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate { mProgressBar.visibility = View.GONE }
                .subscribe({ handleGroupsList(it) }, { it.printStackTrace() })

    }

    private fun handleGroupsList(groups: List<Group>) {
        mGroups.clear()
        mGroups.addAll(groups)
        val arrayGroups = Array<String>(groups.size, { index-> groups[index].name ?: "" })
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayGroups)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mSpinner.adapter = adapter
    }

    private fun inject() {
        (application as AppDelegate).injector?.inject(this)
    }


}

