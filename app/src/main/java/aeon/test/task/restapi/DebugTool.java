package aeon.test.task.restapi;

import android.content.Context;

import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.Interceptor;

public class DebugTool {
    public static Interceptor createChuckInterceptor(Context context) {
        return new ChuckInterceptor(context);
    }

}
