package balogh.zoltan.todo.mock.interceptor;

import android.net.Uri;

import java.util.HashMap;
import java.util.Map;

import balogh.zoltan.todo.model.Todo;
import balogh.zoltan.todo.network.NetworkConfig;
import balogh.zoltan.todo.utils.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static balogh.zoltan.todo.mock.interceptor.MockHelper.makeResponse;

public class TodoMock {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        Map<Long, Todo> todos = new HashMap<Long, Todo>();

        Todo todo1 = new Todo(2L, "Todo1", "Todo todo todo", false);
        Todo todo2 = new Todo(3L, "Todo2", "Todo todo todo", false);
        Todo todo3 = new Todo(3L, "Todo3", "Todo todo todo", true);

        todos.put(todo1.getId(), todo1);
        todos.put(todo2.getId(), todo2);
        todos.put(todo3.getId(), todo3);

        String responseString;
        int responseCode;
        Headers headers = request.headers();


        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "todo") && request.method().equals("Get")) {
            responseString = GsonHelper.getGson().toJson(todos.values().toArray());
            responseCode = 200;
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "todo") && request.method().equals("Post")) {
            responseString = "OK";
            responseCode = 200;
        } else if (uri.getPath().matches("\\" + NetworkConfig.ENDPOINT_PREFIX + "todo\\/" + "\\d+") && request.method().equals("Post")) {
            int i = uri.getPath().lastIndexOf("/");
            long id = Long.parseLong(uri.getPath().substring(i));
            Todo ret = todos.get(id);
            if (ret != null) {
                responseString = GsonHelper.getGson().toJson(ret);
                responseCode = 200;
            } else {
                responseString = "NOT FOUND";
                responseCode = 404;
            }
        } else if (uri.getPath().matches("\\" + NetworkConfig.ENDPOINT_PREFIX + "todo\\/" + "\\d+") && request.method().equals("Put")) {
            responseString = "OK";
            responseCode = 200;
        } else if (uri.getPath().matches("\\" + NetworkConfig.ENDPOINT_PREFIX + "todo\\/" + "\\d+") && request.method().equals("Delete")) {
            responseString = "OK";
            responseCode = 200;
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}