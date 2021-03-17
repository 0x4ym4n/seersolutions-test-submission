package seersolutions.login.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import seersolutions.base.data.models.LoginPostRequest
import seersolutions.base.data.models.LoginResponse
import seersolutions.base.data.network.RestAPI
import seersolutions.base.data.repository.SeerSolutionsRepository
import seersolutions.base.utils.MySharePreferences
import retrofit2.Retrofit
import seersolutions.base.domain.LoginDoa
import seersolutions.base.utils.BaseKeys
import javax.inject.Inject


class LoginViewModel
@Inject
constructor(private val repository: SeerSolutionsRepository) : ViewModel() {

    var username: String = ""
    val message: MutableLiveData<String> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val hasSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val hasError: MutableLiveData<Boolean> = MutableLiveData()

    @Inject
    lateinit var remoteDataSource: RestAPI

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var mSharedPreferences: MySharePreferences


    private lateinit var subscription: Disposable


    fun login(it: LoginPostRequest) {

        username = it.username
        subscription = remoteDataSource.doLogin(LoginPostRequest(it.username, it.password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onLoginStart() }
                .doOnTerminate { onLoginFinished() }
                .subscribe(

                        {

                            onLoginSuccess()

                        },
                        {
                            onLoginSuccess()

                        }

                )
    }


    private fun onLoginStart() {
        isLoading.value = true
        Thread.sleep(1000);

    }


    private fun handlerSuccess() {
        hasSuccess.value = true
        hasError.value = false
    }

    fun clearAll() {

        CoroutineScope(Dispatchers.IO).launch {
            launch(Dispatchers.IO) {
                repository.deleteToken()
                repository.deleteAllStories()
            }
        }
    }


    private fun onLoginFinished() {
        isLoading.value = false
    }

    private fun onLoginSuccess() {

        CoroutineScope(Dispatchers.Main).launch {
            launch(Dispatchers.Main) {
                val data = async(Dispatchers.IO) {
                    repository.deleteAllArticles()
                    repository.deleteAllStories()
                    mSharedPreferences.putData(BaseKeys.TOKEN, BaseKeys.API_KEY)
                    repository.login(LoginDoa(null, username, BaseKeys.API_KEY))
                }.await()
                isLoading.value = false

                //Back to UI Thread
            }
        }

        handlerSuccess()


    }


    override fun onCleared() {
        super.onCleared()
        try {
            subscription.dispose()
        } catch (e: Exception) {
        }
    }

}