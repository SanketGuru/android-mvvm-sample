package sanketguru.com.sample.test

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import sanketguru.com.sample.apiservices.Geoname


/**
 * Created by sanket on 12/3/2017.
 */

class CityResponse {
    @SerializedName("geonames")
    @Expose
    var geonames = mutableListOf<Geoname>()
}
