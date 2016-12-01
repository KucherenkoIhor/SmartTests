package ughtu.com.smarttests.shared

/**
 * Created by igor on 01.12.16.
 */
import android.app.Application
import dagger.Component
import ughtu.com.smarttests.views.LoginActivity
import ughtu.com.smarttests.views.SubjectsActivity
import javax.inject.Singleton

class AppDelegate : Application() {

    var injector: AppInjector? = null

    @Singleton
    @Component(modules = arrayOf(NewsModule::class))
    interface AppInjector {

        fun inject(activity: LoginActivity)

        fun inject(activity: SubjectsActivity)

    }

    override fun onCreate() {
        super.onCreate()
        injector = DaggerAppDelegate_AppInjector.builder().build()

    }
}