package sanketguru.com.sample.apiservices;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

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
        //  httpClient.addNetworkInterceptor()
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        httpClient.addInterceptor(loggingInterceptor);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                final Request original = chain.request();
                final HttpUrl originalHttpUrl = original.url();

                final HttpUrl url = originalHttpUrl.newBuilder()
                        //    .addQueryParameter("username", "demo")
                        .build();

                // Request customization: add request headers
                final Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                final Request request = requestBuilder.build();
                Timber.v("Request url  : %s", request.url().toString());
                Timber.v("Request body : %s", request.body().toString());
                Timber.v("Request head : %s", request.headers().toString());
                return chain.proceed(request);
            }
        });

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
