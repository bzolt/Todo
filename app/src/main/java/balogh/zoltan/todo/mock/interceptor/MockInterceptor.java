package balogh.zoltan.todo.mock.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class MockInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;//process(chain.request());
    }

//    public Response process(Request request) {
//
//        Uri uri = Uri.parse(request.url().toString());
//
//        Log.d("Test Http Client", "URL call: " + uri.toString());
//        Headers headers = request.headers();
//
//
//        if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "todo")) {
//            return TodoMock.process(request);
//        }
//
//        if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "user")) {
//            return UserMock.process(request);
//        }
//
//
//        return makeResponse(request, headers, 404, "Unknown");
//
//    }

}