package sanketguru.com.sample.apiservices
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by sanket on 12/10/2017.
 */
/**Wrapper Class around Every response*/
class ApiResponse<E> {
    @SerializedName("statusCode")
    @Expose
    var statusCode = 400
    @SerializedName("isSuccess")
    @Expose
    var isSuccess = false
    @SerializedName("errorMessage")
    @Expose
    var errorMessage = ""
    @SerializedName("payload")
    @Expose
    var payload: E? = null
}