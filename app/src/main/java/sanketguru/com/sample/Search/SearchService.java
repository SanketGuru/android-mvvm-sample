package sanketguru.com.sample.Search;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Bhavesh on 12-12-2017.
 */

public interface SearchService {
    @GET("Vacancy/SerachVacancy/{VacID:{vacID}}")
    Observable<SearchVacancyResponce> getSearchResponse(@Path("vacID") String query);



}
