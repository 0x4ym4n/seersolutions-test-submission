package seersolutions.login

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import seersolutions.base.data.models.LoginPostRequest
import seersolutions.base.utils.BaseKeys
import seersolutions.base.utils.InjectUtils
import seersolutions.base.utils.Utils
import seersolutions.login.component.DaggerLoginComponent
import seersolutions.login.ui.LoginViewModel
import seersolutions.loginmodule.R
import seersolutions.loginmodule.databinding.ActivityLoginBinding
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LoginViewModel
    private var errorSnackbar: Snackbar? = null
    val context: Activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerLoginComponent
            .builder()
            .baseComponent(InjectUtils.provideBaseComponent(applicationContext))
            .build()
            .inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LoginViewModel::class.java)

        setupToolbar()

        if (intent != null && intent.hasExtra(BaseKeys.UNAUTHORIZED)) {
            viewModel.clearAll()
            showError(BaseKeys.UNAUTHORIZED)
        }



        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.data = LoginPostRequest("", "")


        binding.login.setOnClickListener {
            if (Utils.isNetConnected(context)) {
                if(binding.username.text!!.length< 3){
                    binding.username.setError(getString(R.string.valid_username))
                        return@setOnClickListener
                }
                if(binding.password.text!!.length< 3){
                    binding.password.setError(getString(R.string.invalid_password))
                    return@setOnClickListener
                }
                doLogin()
            } else {

                Utils.showAlert(context, getString(R.string.check_internet_connection))
            }
        }



        viewModel.message.observe(this, Observer {
            Utils.showAlert(context, it)
        })

        viewModel.hasSuccess.observe(this, Observer {
            if (it) {
                navigateToDashboard()
            }
        })

    }

    private fun setupToolbar() {
        setSupportActionBar(binding.includeToolbar.toolbar)
        binding.includeToolbar.toolbar.title = getString(R.string.login)
    }

    fun doLogin() {
        binding.data?.let {
            viewModel.login(it)
        }
    }


    private fun navigateToDashboard() {

        val intent = Intent().setClassName(this, "seersolutions.dashboard.ActivityDashboard")
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


    private fun showError(message: String) {
        errorSnackbar =
            Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).setAction("", null)
        errorSnackbar!!.setActionTextColor(Color.BLUE)
        errorSnackbar!!.show()

    }


}