package com.seersolutions.aymantest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seersolutions.aymantest.main.di.component.DaggerMainActivityComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import seersolutions.base.data.repository.SeerSolutionsRepository
import seersolutions.base.utils.BaseKeys
import seersolutions.base.utils.InjectUtils
import seersolutions.base.utils.MySharePreferences
import seersolutions.login.LoginActivity
import seersolutions.dashboard.ActivityDashboard
import javax.inject.Inject

class SplashScreen : AppCompatActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    @Inject
    lateinit var repository: SeerSolutionsRepository

    @Inject
    lateinit var mSharedPreferences: MySharePreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        DaggerMainActivityComponent
            .builder()
            .baseComponent(InjectUtils.provideBaseComponent(applicationContext))
            .build()
            .inject(this)




        checkLogin()

    }

    private fun checkLogin() {


        compositeDisposable.add(repository.getToken()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .doAfterTerminate { }
            .subscribe(
                {
                    if (it.isEmpty()) {
                        // fake splash screen loading
                        Thread.sleep(1500);
                        val intent = Intent(this, LoginActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    } else {
                        // fake splash screen loading
                        Thread.sleep(1500);
                        mSharedPreferences.putData(BaseKeys.TOKEN, it[0].token)
                        val intent = Intent(this, ActivityDashboard::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                    }
                },
                { }
            )
        )
    }


}
