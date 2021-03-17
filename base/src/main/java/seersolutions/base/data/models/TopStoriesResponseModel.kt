package seersolutions.base.data.models

import com.google.gson.annotations.SerializedName

class TopStoriesResponseModel {
    /**
     * status : OK
     * copyright : Copyright (c) 2021 The New York Times Company. All Rights Reserved.
     * section : Arts
     * last_updated : 2021-03-16T17:32:31-04:00
     * num_results : 34
     * results : [{"section":"arts","subsection":"music","title":"How Honest Can Demi Lovato Be?","abstract":"The singer is opening up about her queerness, her near fatal overdose and her journey to living her truth. \u201cI\u2019m ready to feel like myself,\u201d she said.","url":"https://www.nytimes.com/2021/03/16/arts/music/demi-lovato-interview.html","uri":"nyt://article/b4c2db3e-a5ae-5432-a485-e0b9236b1510","byline":"By Caryn Ganz","item_type":"Article","updated_date":"2021-03-16T19:15:2å4-04:00","created_date":"2021-03-16T17:30:11-04:00","published_date":"2021-03-16T17:30:11-04:00","material_type_facet":"","kicker":"","des_facet":["Pop and Rock Music","Drug Abuse and Traffic","Mental Health and Disorders","Documentary Films and Programs","Dancing With the Devil ... The Art of Starting Over (Album)","Content Type: Personal Profile"],"org_facet":[],"per_facet":["Lovato, Demi"],"geo_facet":[],"multimedia":[{"url":"https://static01.nyt.com/images/2021/03/21/arts/21DEMI-LOVATO-promo-new/merlin_184845153_e44c6cf0-0e3d-4ae8-9a09-6453c8c075db-superJumbo.jpg","format":"superJumbo","height":2048,"width":1639,"type":"image","subtype":"photo","caption":"","copyright":"Ryan Pfluger for The New York Times"},{"url":"https://static01.nyt.com/images/2021/03/21/arts/21DEMI-LOVATO-promo-new/21DEMI-LOVATO-promo-new-thumbStandard.jpg","format":"Standard Thumbnail","height":75,"width":75,"type":"image","subtype":"photo","caption":"","copyright":"Ryan Pfluger for The New York Times"},{"url":"https://static01.nyt.com/images/2021/03/21/arts/21DEMI-LOVATO-promo-new/21DEMI-LOVATO-promo-new-thumbLarge.jpg","format":"thumbLarge","height":150,"width":150,"type":"image","subtype":"photo","caption":"","copyright":"Ryan Pfluger for The New York Times"},{"url":"https://static01.nyt.com/images/2021/03/21/arts/21DEMI-LOVATO-promo-new/21DEMI-LOVATO-promo-new-mediumThreeByTwo210.jpg","format":"mediumThreeByTwo210","height":140,"width":210,"type":"image","subtype":"photo","caption":"","copyright":"Ryan Pfluger for The New York Times"},{"url":"https://static01.nyt.com/images/2021/03/21/arts/21DEMI-LOVATO-promo-new/merlin_184845153_e44c6cf0-0e3d-4ae8-9a09-6453c8c075db-articleInline.jpg","format":"Normal","height":237,"width":190,"type":"image","subtype":"photo","caption":"","copyright":"Ryan Pfluger for The New York Times"}],"short_url":"https://nyti.ms/3vAvLnd"}]
     */
    @SerializedName("status")
    var status: String? = null

    @SerializedName("copyright")
    var copyright: String? = null

    @SerializedName("section")
    var section: String? = null

    @SerializedName("last_updated")
    var lastUpdated: String? = null

    @SerializedName("num_results")
    var numResults = 0

    /**
     * section : arts
     * subsection : music
     * title : How Honest Can Demi Lovato Be?
     * abstract : The singer is opening up about her queerness, her near fatal overdose and her journey to living her truth. “I’m ready to feel like myself,” she said.
     * url : https://www.nytimes.com/2021/03/16/arts/music/demi-lovato-interview.html
     * uri : nyt://article/b4c2db3e-a5ae-5432-a485-e0b9236b1510
     * byline : By Caryn Ganz
     * item_type : Article
     * updated_date : 2021-03-16T19:15:2å4-04:00
     * created_date : 2021-03-16T17:30:11-04:00
     * published_date : 2021-03-16T17:30:11-04:00
     * material_type_facet :
     * kicker :
     * des_facet : ["Pop and Rock Music","Drug Abuse and Traffic","Mental Health and Disorders","Documentary Films and Programs","Dancing With the Devil ... The Art of Starting Over (Album)","Content Type: Personal Profile"]
     * org_facet : []
     * per_facet : ["Lovato, Demi"]
     * geo_facet : []
     * multimedia : [{"url":"https://static01.nyt.com/images/2021/03/21/arts/21DEMI-LOVATO-promo-new/merlin_184845153_e44c6cf0-0e3d-4ae8-9a09-6453c8c075db-superJumbo.jpg","format":"superJumbo","height":2048,"width":1639,"type":"image","subtype":"photo","caption":"","copyright":"Ryan Pfluger for The New York Times"},{"url":"https://static01.nyt.com/images/2021/03/21/arts/21DEMI-LOVATO-promo-new/21DEMI-LOVATO-promo-new-thumbStandard.jpg","format":"Standard Thumbnail","height":75,"width":75,"type":"image","subtype":"photo","caption":"","copyright":"Ryan Pfluger for The New York Times"},{"url":"https://static01.nyt.com/images/2021/03/21/arts/21DEMI-LOVATO-promo-new/21DEMI-LOVATO-promo-new-thumbLarge.jpg","format":"thumbLarge","height":150,"width":150,"type":"image","subtype":"photo","caption":"","copyright":"Ryan Pfluger for The New York Times"},{"url":"https://static01.nyt.com/images/2021/03/21/arts/21DEMI-LOVATO-promo-new/21DEMI-LOVATO-promo-new-mediumThreeByTwo210.jpg","format":"mediumThreeByTwo210","height":140,"width":210,"type":"image","subtype":"photo","caption":"","copyright":"Ryan Pfluger for The New York Times"},{"url":"https://static01.nyt.com/images/2021/03/21/arts/21DEMI-LOVATO-promo-new/merlin_184845153_e44c6cf0-0e3d-4ae8-9a09-6453c8c075db-articleInline.jpg","format":"Normal","height":237,"width":190,"type":"image","subtype":"photo","caption":"","copyright":"Ryan Pfluger for The New York Times"}]
     * short_url : https://nyti.ms/3vAvLnd
     */
    @SerializedName("results")
    var results: List<ResultsBean>? = null

    class ResultsBean {
        @SerializedName("section")
        var section: String? = null

        @SerializedName("subsection")
        var subsection: String? = null

        @SerializedName("title")
        var title: String? = null

        @SerializedName("abstract")
        var abstractX: String? = null

        @SerializedName("url")
        var url: String? = null

        @SerializedName("uri")
        var uri: String? = null

        @SerializedName("byline")
        var byline: String? = null

        @SerializedName("item_type")
        var itemType: String? = null

        @SerializedName("updated_date")
        var updatedDate: String? = null

        @SerializedName("created_date")
        var createdDate: String? = null

        @SerializedName("published_date")
        var publishedDate: String? = null

        @SerializedName("material_type_facet")
        var materialTypeFacet: String? = null

        @SerializedName("kicker")
        var kicker: String? = null

        @SerializedName("short_url")
        var shortUrl: String? = null

        @SerializedName("des_facet")
        var desFacet: List<String>? = null

        @SerializedName("org_facet")
        var orgFacet: List<*>? = null

        @SerializedName("per_facet")
        var perFacet: List<String>? = null

        @SerializedName("geo_facet")
        var geoFacet: List<*>? = null

        /**
         * url : https://static01.nyt.com/images/2021/03/21/arts/21DEMI-LOVATO-promo-new/merlin_184845153_e44c6cf0-0e3d-4ae8-9a09-6453c8c075db-superJumbo.jpg
         * format : superJumbo
         * height : 2048
         * width : 1639
         * type : image
         * subtype : photo
         * caption :
         * copyright : Ryan Pfluger for The New York Times
         */
        @SerializedName("multimedia")
        var multimedia: List<MultimediaBean>? = null

        class MultimediaBean {
            @SerializedName("url")
            var url: String? = null

            @SerializedName("format")
            var format: String? = null

            @SerializedName("height")
            var height = 0

            @SerializedName("width")
            var width = 0

            @SerializedName("type")
            var type: String? = null

            @SerializedName("subtype")
            var subtype: String? = null

            @SerializedName("caption")
            var caption: String? = null

            @SerializedName("copyright")
            var copyright: String? = null
        }
    }
}