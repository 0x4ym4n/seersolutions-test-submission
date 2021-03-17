package seersolutions.base.data.models

import com.google.gson.annotations.SerializedName

class errorBody {
    /**
     * faultstring : Rate limit quota violation. Quota limit  exceeded. Identifier : fec99864-013e-4bf5-825e-6d7f51953892
     * detail : {"errorcode":"policies.ratelimit.QuotaViolation"}
     */
    @SerializedName("fault")
    var fault: FaultBean? = null

    class FaultBean {
        @SerializedName("faultstring")
        var faultstring: String? = null

        /**
         * errorcode : policies.ratelimit.QuotaViolation
         */
        @SerializedName("detail")
        var detail: DetailBean? = null

        class DetailBean {
            @SerializedName("errorcode")
            var errorcode: String? = null
        }
    }
}