package balogh.zoltan.todo.mock;

import balogh.zoltan.todo.mock.interceptor.MockInterceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MockHttpServer {

    public static Response call(Request request) {
        MockInterceptor mockInterceptor = new MockInterceptor();
        return mockInterceptor.process(request);
    }
}