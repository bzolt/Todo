package balogh.zoltan.todo.network.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {

    /**
     * Logs user into the system
     *
     * @return Call<String>
     */

    @GET("user/login")
    Call<String> userLoginGet();


}
