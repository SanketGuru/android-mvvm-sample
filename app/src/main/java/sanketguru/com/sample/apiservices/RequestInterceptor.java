package sanketguru.com.sample.apiservices;

import android.provider.MediaStore;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import timber.log.Timber;

/**
 * Created by Sanket Gurav on 12/12/2017.
 */

public class RequestInterceptor implements Interceptor {
    private boolean debug = false;
    private boolean encrypt = false;

    /**
     * */
    public RequestInterceptor(boolean debug, boolean encrypt) {
        this.debug = debug;
        this.encrypt = encrypt;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request original = chain.request();
        final HttpUrl originalHttpUrl = original.url();

        final HttpUrl url = originalHttpUrl.newBuilder()
                //    .addQueryParameter("username", "demo")
                .build();

        // Request customization: add request headers
        final Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        final Request request = requestBuilder.build();
        if (debug) {
            Timber.v("Request url  : [ %s ] -> [ %s ]", request.method(), request.url().toString());
            Timber.v("Request body : %s", request.body());
            Timber.v("Request head : %s", request.headers());
        }
        if (encrypt) {
            //TODO encrypt request data here
        }
        Response response = chain.proceed(request);
        response.body();

        if (encrypt) {
            //TODO decryp request data here
        }
        MediaType mtype = response.body().contentType();
        ResponseBody body = ResponseBody.create( mtype ,response.body().bytes());
        response.newBuilder().body(body).build();


        return response.newBuilder().body(body).build();
    }
}
