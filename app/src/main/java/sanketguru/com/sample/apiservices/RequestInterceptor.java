package sanketguru.com.sample.apiservices;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
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

        Request request = requestBuilder.build();
        Request requestEncrypt = null;
        RequestBody requestBodyEncrypted = null;
        MediaType mtype = request.body().contentType();
        if (debug) {
            Timber.v("Request url  : [ %s ] -> [ %s ]", request.method(), request.url().toString());
            Timber.v("Request body : %s", request.body());
            Timber.v("Request head : %s", request.headers());
        }
        final Buffer buffer = new Buffer();
        if (encrypt) {
            //TODO encrypt request data here

            //region encryptying header
            Headers heads = request.headers();
            Headers.Builder headBuilder = new Headers.Builder();
            for (int i = 0; i < heads.size(); i++) {
                headBuilder.add(heads.name(i), SecurityUtil.encrypt(heads.value(i)));
            }
            if (request.body() != null) {
              //  requestBodyEncrypted = RequestBody.create(mtype, SecurityUtil.encrypt(request.body().toString()).getBytes());

                if (request != null)
                    request.body().writeTo(buffer);
                requestBodyEncrypted = RequestBody.create(mtype,SecurityUtil.encrypt(buffer.toString()));
                if (original.method().equalsIgnoreCase("Post"))
                    requestBuilder.post(requestBodyEncrypted);
            }
            requestEncrypt = requestBuilder.headers(headBuilder.build()).build();
            if (debug) {
                Timber.v("Request url  : [ %s ] -> [ %s ]", requestEncrypt.method(), requestEncrypt.url().toString());
                Timber.v("Request body encrypt: %s", SecurityUtil.encrypt(buffer.toString()));
                Timber.v("Request head encrypt: %s", requestBuilder.headers(headBuilder.build()).build());
            }
            //endregion
            buffer.close();

        }
        Response response = chain.proceed(requestEncrypt);
      //  ResponseBody respBody=  response.body();


        ResponseBody respBody= ResponseBody.create(mtype, SecurityUtil.decrypt(new String(response.body().bytes())));
        if (encrypt) {
            //TODO decryp response data here
        }

//        ResponseBody body = ResponseBody.create(mtype, response.body().bytes());
      //  response.newBuilder().body(respBody).build();


        return response.newBuilder().body(respBody).build();
    }
}
