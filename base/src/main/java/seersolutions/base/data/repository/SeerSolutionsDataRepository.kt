package seersolutions.base.data.repository

import seersolutions.base.data.room.RoomDoa
import seersolutions.base.domain.ArticleDoa
import seersolutions.base.domain.LoginDoa
import seersolutions.base.domain.StoryDoa
import javax.inject.Inject


class SeerSolutionsDataRepository @Inject constructor(
    private val dao: RoomDoa
) : SeerSolutionsRepository {
    override fun login(item: LoginDoa) {
        dao.login(item)
    }

    override fun insertStory(item: StoryDoa) = dao.insertStory(item)


    override fun deleteAllStories() {
        dao.deleteAllStories()

    }


    override fun insertArticle(item: ArticleDoa) = dao.insertArticle(item)


    override fun deleteAllArticles() {
        dao.deleteAllArticles()

    }

    override fun deleteToken() {
        dao.deleteToken()
    }

    override fun listAllStories() = dao.listAllStories()
    override fun listAllArticles() = dao.listAllArticles()
    override fun getToken() = dao.getToken()

}