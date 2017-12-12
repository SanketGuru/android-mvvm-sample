package sanketguru.com.sample.apiservices;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sanketguru.com.sample.BuildConfig;
/*retrofit logging
https://stackoverflow.com/questions/32514410/logging-with-retrofit-2*/

/**
 * Created by Sanket Gurav on 12/11/2017.
 */

public class RetrofitHelper {

    /**
     * The CityService communicates with the json api of the city provider.
     */
    public WebServices getWebService() {
        final Retrofit retrofit = createRetrofit();
        return retrofit.create(WebServices.class);
    }

    /**
     * This custom client will append the "username=demo" query after every request.
     */
    private OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(new RequestInterceptor(BuildConfig.DEBUG, true));
        httpClient.addNetworkInterceptor(new ResponseInterceptor(BuildConfig.DEBUG, true));
        return httpClient.build();
    }

    /**
     * Creates a pre configured Retrofit instance
     */
    private String baseUrl = "https://api.github.com";

    private Retrofit createRetrofit() {
        return new Retrofit.Builder().
                baseUrl(ApiConfig.BASE_URL + ApiConfig.API_VERSION)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // <- add this
                .client(createOkHttpClient())
                .build();
    }
}
