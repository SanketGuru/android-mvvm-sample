package sanketguru.com.sample.test


import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by sanket on 12/3/2017.
 */

interface CityService {

    /**
     * This method returns all cities within a given bounding box
     *
     * Example from the api docs: citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo
     *
     * @param north    bounding box north
     * @param south    bounding box south
     * @param east     bounding box east
     * @param west     bounding box west
     * @param lang     geoname output language
     */
    @GET("citiesJSON")
    fun queryGeonames(@Query("north") north: Int, @Query("south") south: Double,
                      @Query("east") east: Double, @Query("west") west: Double, @Query("lang") lang: String): Single<CityResponse>

    @GET("citiesJSON")
    fun queryGeonamesO(@Query("north") north: Int, @Query("south") south: Double,
                       @Query("east") east: Double, @Query("west") west: Double, @Query("lang") lang: String): Observable<CityResponse>
}