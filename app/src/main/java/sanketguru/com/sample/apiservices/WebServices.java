package sanketguru.com.sample.apiservices;

/**
 * Created by Sanket Gurav on 12/11/2017.
 */

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WebServices {
    @GET("appcontainer/SomeAction")
    Observable<ApiResponse<Object>> getDashBoard();

    @GET("appcontainer/SomeOtherAction")
    Observable<ApiResponse<List<Object>>> getMatchDetails();
}
