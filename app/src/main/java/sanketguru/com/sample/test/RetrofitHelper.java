package sanketguru.com.sample.test;

/**
 * Created by sanket on 12/3/2017.
 */

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sanketguru.com.sample.BuildConfig;
import sanketguru.com.sample.Search.SearchService;
import sanketguru.com.sample.apiservices.RequestInterceptor;
import sanketguru.com.sample.apiservices.ResponseInterceptor;
import timber.log.Timber;

/**
 * This class initializes retrofit with a default configuration.
 * You can use this class to initialize the different services.
 */

public class RetrofitHelper {

    /**
     * The CityService communicates with the json api of the city provider.
     */
    public CityService getCityService() {
        final Retrofit retrofit = createRetrofit();
        return retrofit.create(CityService.class);
    }

    public SearchService getSearchService() {
        final Retrofit retrofit = createRetrofit();
        return retrofit.create(SearchService.class);
    }

    /**
     * This custom client will append the "username=demo" query after every request.
     */
    private OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(new RequestInterceptor(BuildConfig.DEBUG,true));
      //  httpClient.addNetworkInterceptor(new ResponseInterceptor(BuildConfig.DEBUG,true));

        return httpClient.build();
    }

    static  final String newbaseUrl="http://122.184.137.84:85/PartnerPortalMobileApp/api/";
    /**
     * Creates a pre configured Retrofit instance
     */
    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
               // .baseUrl("http://api.geonames.org/")
                .baseUrl(newbaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // <- add this
                .client(createOkHttpClient())
                .build();
    }
}
