package seersolutions.dashboard

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.search_view.view.*
import seersolutions.base.domain.ArticleDoa
import seersolutions.base.domain.StoryDoa
import seersolutions.base.utils.InjectUtils
import seersolutions.base.utils.RxEmitter
import seersolutions.base.utils.Utils
import seersolutions.dashboard.databinding.ActivityDashboardBinding
import seersolutions.dashboard.di.component.DaggerFeatureOneComponent
import seersolutions.dashboard.ui.DashboardViewModel
import seersolutions.dashboard.utils.Keys
import javax.inject.Inject


class ActivityDashboard : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var viewModel: DashboardViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerFeatureOneComponent
            .builder()
            .baseComponent(InjectUtils.provideBaseComponent(applicationContext))
            .build()
            .inject(this)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        setupToolbar()
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this



        setupStoriesRecyclerView()
        setupTextEditor()
        setArticlesRecyclerView()
        observeStoriesList()
        observeArticlesList()
        refreshStoriesAdapter()
        viewModel.getStoriesFromLocalRepository()









    }


    private fun setupTextEditor(){


        binding.searchBar.main.search_btn.setOnClickListener {

            if (binding.searchBar.main.inner.search_text.text.length < 3) {


                binding.searchBar.main.inner.search_text.setError(getString(R.string.short_query))
            } else {
                Utils.hideSoftKeyboard(this)
                viewModel.getArticlesRemoteDataSource(binding.searchBar.main.inner.search_text.text.toString())


            }

            binding.searchBar.main.inner.search_text.onSubmit {


                if (binding.searchBar.main.inner.search_text.text.length < 3) {


                    binding.searchBar.main.inner.search_text.setError(getString(R.string.short_query))
                } else {
                    Utils.hideSoftKeyboard(this)

                    viewModel.getArticlesRemoteDataSource(binding.searchBar.main.inner.search_text.text.toString())


                }
            }


        }
    }
    fun EditText.onSubmit(func: () -> Unit) {
        setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                func()
            }

            true

        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.includeToolbar.toolbar)
        binding.includeToolbar.toolbar.title = getString(R.string.dashboard)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getStoriesRemoteDataSource()
    }


    private fun setupStoriesRecyclerView() {

        val recyclerView = binding.storiesView
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
    }


    private fun setArticlesRecyclerView() {

        val recyclerView = binding.articlesView
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
    }


    private fun observeStoriesList() {

        viewModel.storiesList.observe(this, androidx.lifecycle.Observer {
            val item: List<StoryDoa> = it as List<StoryDoa>
            if (item.isNotEmpty()) {
                setupStoriesAdapter(item)
            }
        })
    }


    private fun observeArticlesList() {

        viewModel.articlesList.observe(this, androidx.lifecycle.Observer {
            val item: List<ArticleDoa> = it as List<ArticleDoa>
            if (item.isNotEmpty()) {
                setupArticlesAdapter(item)
            }
        })

        viewModel.message.observe(this, androidx.lifecycle.Observer {

            binding.errorMsg.text = it.toString()


        })
    }


    private fun setupStoriesAdapter(it: List<StoryDoa>) {

        binding.storiesView.adapter = null
        binding.storiesView.adapter = StoriesAdapter(it, this)


    }


    private fun setupArticlesAdapter(it: List<ArticleDoa>) {


        binding.articlesView.adapter = null
        binding.articlesView.adapter = ArticlesAdapter(it, this)


    }


    private fun refreshStoriesAdapter() {

        RxEmitter.subscribe(Keys.REFRESH, this, Consumer {
            val item: List<StoryDoa> = it as List<StoryDoa>
            setupStoriesAdapter(item)


        })
    }


}
