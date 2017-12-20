package sanketguru.com.sample.Search;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Bhavesh on 12-12-2017.
 */

public interface SearchService {
    @GET("Vacancy/SerachVacancy/{VacID:{vacID}}")
    Observable<SearchVacancyResponce> getSearchResponse(@Path("vacID") String query);


    @GET("Vacancy/SerachVacancy/{VacID:{vacID}}")
    Observable<SearchVacancyResponce> getSearchResponseBody(@Path("vacID") String query, @HeaderMap Map<String,String>  header);


}
