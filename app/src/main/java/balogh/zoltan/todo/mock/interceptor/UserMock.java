package balogh.zoltan.todo.mock.interceptor;

import android.net.Uri;
import android.util.Log;

import balogh.zoltan.todo.model.User;
import balogh.zoltan.todo.network.NetworkConfig;
import balogh.zoltan.todo.repository.MemoryRepository;
import balogh.zoltan.todo.utils.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static balogh.zoltan.todo.mock.interceptor.MockHelper.makeResponse;

public class UserMock {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        User user = new User(1L, "user", "user");

        String responseString;
        int responseCode;
        Headers headers = request.headers();


        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "user/login") && request.method().equals("Post")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            // TODO console figyelése
            Log.d("asd", request.body().toString());
            User postedUser = GsonHelper.getGson().fromJson(request.body().toString(), User.class);
            Log.d("asd", postedUser.getName());
            if (postedUser.getName().equals(user.getName()) && postedUser.getPassword().equals(user.getPassword())) {
                responseString = "OK";
                responseCode = 200;
            } else {
                responseString = "INVALID";
                responseCode = 401;
            }
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}