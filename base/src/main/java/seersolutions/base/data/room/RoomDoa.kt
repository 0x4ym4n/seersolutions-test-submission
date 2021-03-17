package seersolutions.base.data.room

import androidx.room.*
import io.reactivex.Maybe
import seersolutions.base.domain.ArticleDoa
import seersolutions.base.domain.LoginDoa
import seersolutions.base.domain.StoryDoa


@Dao
interface RoomDoa {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun login(item: LoginDoa)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStory(item: StoryDoa)


    @Query("SELECT * FROM stories ORDER BY id ASC")
    fun listAllStories(): Maybe<List<StoryDoa>>


    @Query("DELETE FROM stories")
    fun deleteAllStories()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(item: ArticleDoa)


    @Query("SELECT * FROM articles ORDER BY id ASC")
    fun listAllArticles(): Maybe<List<ArticleDoa>>


    @Query("DELETE FROM articles")
    fun deleteAllArticles()



    @Query("SELECT * FROM login")
    fun getToken(): Maybe<List<LoginDoa>>


    @Query("DELETE FROM login")
    fun deleteToken()

}