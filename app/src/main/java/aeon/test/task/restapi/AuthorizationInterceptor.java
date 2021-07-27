package aeon.test.task.restapi;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class AuthorizationInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder()
               // .addHeader("app-key", "12345")
               // .addHeader("v", "1")
                .build());
    }
}
