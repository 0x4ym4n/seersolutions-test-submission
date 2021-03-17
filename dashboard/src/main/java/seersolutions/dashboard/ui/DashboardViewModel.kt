package seersolutions.dashboard.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import seersolutions.base.data.models.TopStoriesResponseModel
import seersolutions.base.data.models.errorBody
import seersolutions.base.data.network.RestAPI
import seersolutions.base.data.repository.SeerSolutionsRepository
import seersolutions.base.domain.StoryDoa
import seersolutions.base.utils.BaseKeys
import seersolutions.base.utils.MySharePreferences
import seersolutions.base.utils.RxEmitter
import seersolutions.dashboard.utils.Keys
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Retrofit
import seersolutions.base.data.models.ArticlesResponseModel
import seersolutions.base.domain.ArticleDoa
import javax.inject.Inject


class DashboardViewModel
@Inject
constructor(private val repository: SeerSolutionsRepository) : ViewModel() {


    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val storiesList: MutableLiveData<List<StoryDoa>> = MutableLiveData()
    val articlesList: MutableLiveData<List<ArticleDoa>> = MutableLiveData()
    var action: String = ""
    val message: MutableLiveData<String> = MutableLiveData()
    val isStoryLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isArticleLoading: MutableLiveData<Boolean> = MutableLiveData()
    val hasStorySuccess: MutableLiveData<Boolean> = MutableLiveData()
    val hasArticleSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val hasError: MutableLiveData<Boolean> = MutableLiveData()
    private val isStoryEmpty: MutableLiveData<Boolean> = MutableLiveData()
    val isArticlesEmpty: MutableLiveData<Boolean> = MutableLiveData()

    @Inject
    lateinit var remoteDataSource: RestAPI

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var mSharedPreferences: MySharePreferences


    private lateinit var subscription: Disposable


    private fun handleStorySucess(type: String) {
        action = type
        hasStorySuccess.value = true
        hasError.value = false
    }


    private fun handleArticleSucess(type: String) {
        hasArticleSuccess.value = true
    }


    private fun handlerError(type: String) {
        action = type
        hasArticleSuccess.value = false
        hasError.value = true
    }


    fun getStoriesFromLocalRepository() {
        compositeDisposable.add(repository.listAllStories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isStoryLoading.value = true }
            .doAfterTerminate { isStoryLoading.value = false }
            .subscribe(
                {
                    storiesList.value = it
                    RxEmitter.publish(Keys.REFRESH, it)
                    handleStorySucess("ALL")
                    isStoryEmpty.value = it.isEmpty()

                },
                {
                }
            )
        )
    }


    fun getStoriesRemoteDataSource() {
        isStoryLoading.value = true
        subscription = remoteDataSource.getTopStories(BaseKeys.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveStoriesListStart() }
            .subscribe(

                {
                    onRetrieveStoriesListSuccess(it)
                },
                {

                }
            )
    }


    fun getArticlesRemoteDataSource(query: String) {
        isArticleLoading.value = true
        hasError.value = false
        subscription = remoteDataSource.getArticleSearch(query, BaseKeys.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveArticlesStart() }
                .subscribe(

                    {
                        onRetrieveArticlesListSuccess(it)
                    },
                    {

                        onRemoteError(it)

                    }
                )
    }


    fun getArticlesFromLocalRepository() {
        compositeDisposable.add(repository.listAllArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isArticleLoading.value = true }
            .doAfterTerminate { isArticleLoading.value = false }
            .subscribe(
                {

                    articlesList.value = it
                    RxEmitter.publish(Keys.REFRESH_ARTICLES, it)
                    handleArticleSucess("ALL")
                    isArticlesEmpty.value = it.isEmpty()
                    if(it.isEmpty()){

                        isArticleLoading.value = false
                        hasError.value = true
                        message.value = "No articles found"

                    }

                },
                {
                }
            )
        )
    }



    private fun onRetrieveStoriesListStart() {
        isStoryLoading.value = true
    }


    private fun onRetrieveArticlesStart() {
        isArticleLoading.value = true
    }

    private fun onRetrieveStoriesListSuccess(it: TopStoriesResponseModel) {
        CoroutineScope(Dispatchers.Main).launch {
            launch(Dispatchers.Main) {
                val data = async(Dispatchers.IO) {
                    repository.deleteAllStories()

                    it.results!!.forEach {

                        repository.insertStory(
                            StoryDoa(
                                null,
                                it.title!!,
                                it.byline!!,
                                it.url!!,
                                it.multimedia!![2].url!!
                            )
                        )
                    }
                }.await()
                //Back to UI Thread
                getStoriesFromLocalRepository()
            }
        }


    }




    private fun onRetrieveArticlesListSuccess(it: ArticlesResponseModel) {
        CoroutineScope(Dispatchers.Main).launch {
            launch(Dispatchers.Main) {
                val data = async(Dispatchers.IO) {
                    repository.deleteAllArticles()


                    it.response!!.docs!!.forEach {
                        var  thumb = BaseKeys.ARTICLE_PLACEHOLDER
                        if(it.multimedia!!.isNotEmpty() && it.multimedia!!.size > 4){
                            thumb = "https://www.nytimes.com/"+ it.multimedia!![4].url;

                        }
                        repository.insertArticle(
                            ArticleDoa(
                                null,
                                it.abstractX!!,
                                it.snippet!!,
                                it.webUrl!!,
                                    thumb,
                                it.source!!
                            )
                        )
                    }
                }.await()
                //Back to UI Thread
                getArticlesFromLocalRepository()
            }
        }





    }


    private fun onRemoteError(it: Throwable) {

        if (it is HttpException) {
            if (it.code() == 401) {
                val converter: Converter<ResponseBody, errorBody> = retrofit.responseBodyConverter(
                    errorBody::class.java, emptyArray()
                )
                val error = converter.convert(it.response().errorBody())
                println("error : ${error.fault!!.faultstring}")
                isArticleLoading.value = false
                message.value = error.fault!!.faultstring
            }
            else if (it.code() == 500) {
                isArticleLoading.value = false
                message.value = "Internal server error"
            }
            else {

                try {
                    val converter: Converter<ResponseBody, errorBody> = retrofit.responseBodyConverter(
                        errorBody::class.java, emptyArray()
                    )
                    val error = converter.convert(it.response().errorBody())
                    println("error : ${error.fault!!.faultstring}")
                    isArticleLoading.value = false
                    message.value = error.fault!!.faultstring

                }catch (e:java.lang.Exception){
                    isArticleLoading.value = false
                    message.value = "Unknown error"


                }


            }
            handlerError("ALL")
        } else {
            println("error : $it")
        }
    }

    override fun onCleared() {
        super.onCleared()
        try {
            subscription.dispose()
            if (!compositeDisposable.isDisposed) {
                compositeDisposable.dispose()
            }
        } catch (e: Exception) {

        }

    }
}