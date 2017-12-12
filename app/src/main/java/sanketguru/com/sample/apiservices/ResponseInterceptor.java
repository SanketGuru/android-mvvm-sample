package sanketguru.com.sample.apiservices;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by Sanket Gurav on 12/12/2017.
 */
//https://stackoverflow.com/questions/32294557/retrofit-intercept-responses-globally
public class ResponseInterceptor implements Interceptor {
    private boolean debug = false;
    private boolean decrypt = false;

    /**
     * */
    public ResponseInterceptor(boolean debug, boolean decrypt) {
        this.debug = debug;
        this.decrypt = decrypt;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (debug) {

         //   Timber.v("Response url  : [ %s ] -> [ %s ]", request.method(), request.url().toString());
            Timber.v("Response body : [ %d ] -> %s",   response.code(),response.body());
            Timber.v("Response head : %s", response.headers());
        }
        if (decrypt) {
            //TODO encrypt request data here
        }
        return response;
    }
}
