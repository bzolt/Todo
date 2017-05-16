package balogh.zoltan.todo.mock.interceptor;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;

import balogh.zoltan.todo.network.NetworkConfig;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static balogh.zoltan.todo.mock.interceptor.MockHelper.makeResponse;

public class MockInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        return process(chain.request());
    }

    public Response process(Request request) {

        Uri uri = Uri.parse(request.url().toString());

        Log.d("Test Http Client", "URL call: " + uri.toString());
        Headers headers = request.headers();


        if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "todo")) {
            return TodoMock.process(request);
        }

        if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "user")) {
            return UserMock.process(request);
        }


        return makeResponse(request, headers, 404, "Unknown");

    }

}