package seersolutions.base.data.network

import io.reactivex.Observable
import seersolutions.base.data.models.*
import seersolutions.base.utils.BaseKeys
import retrofit2.http.*


interface RestAPI {
    /**
     * SeerSolutions provided APIs
     */


    @GET(BaseKeys.TOP_STORIES_ENDPOINT)
    fun getTopStories( @Query("api-key") api_key: String): Observable<TopStoriesResponseModel>


    @GET(BaseKeys.ARTICLE_SEARCH_ENDPOINT)
    fun getArticleSearch(@Query("q") q: String,@Query("api-key") api_key: String): Observable<ArticlesResponseModel>




    @POST(BaseKeys.LOGIN_ENDPOINT)
    fun doLogin(@Body body: LoginPostRequest): Observable<LoginResponse>



}