package seersolutions.base.data.repository

import io.reactivex.Maybe
import seersolutions.base.domain.ArticleDoa
import seersolutions.base.domain.LoginDoa
import seersolutions.base.domain.StoryDoa

interface SeerSolutionsRepository {

    fun login(item: LoginDoa)

    fun insertStory(item: StoryDoa)
    fun insertArticle(item: ArticleDoa)

    fun deleteAllStories()
    fun deleteAllArticles()

    fun deleteToken()

    fun listAllStories(): Maybe<List<StoryDoa>>
    fun listAllArticles(): Maybe<List<ArticleDoa>>

    fun getToken(): Maybe<List<LoginDoa>>

}