package ughtu.com.smarttests.shared

/**
 * Created by igor on 01.12.16.
 */
import android.app.Application
import dagger.Component
import ughtu.com.smarttests.views.TestFragment
import ughtu.com.smarttests.views.activities.LecturesActivity
import ughtu.com.smarttests.views.activities.LoginActivity
import ughtu.com.smarttests.views.activities.SubjectsActivity
import ughtu.com.smarttests.views.activities.TestsActivity
import javax.inject.Singleton

class AppDelegate : Application() {

    var injector: AppInjector? = null

    companion object {
        var groupName: String? = null
        var lectureId: Long? = null
    }

    @Singleton
    @Component(modules = arrayOf(NewsModule::class))
    interface AppInjector {

        fun inject(activity: LoginActivity)

        fun inject(activity: SubjectsActivity)

        fun inject(activity: LecturesActivity)

        fun inject(activity: TestsActivity)

        fun inject(fragment: TestFragment)

    }

    override fun onCreate() {
        super.onCreate()
        injector = DaggerAppDelegate_AppInjector.builder().build()

    }
}