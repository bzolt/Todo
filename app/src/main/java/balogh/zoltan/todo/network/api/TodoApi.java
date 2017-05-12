package balogh.zoltan.todo.network.api;

import java.util.List;

import balogh.zoltan.todo.network.model.Todo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TodoApi {

    /**
     * Get all todos of the user
     *
     * @return Call<List<Todo>>
     */

    @GET("todo")
    Call<List<Todo>> todoGet();


    /**
     * Create a new todo for the user
     *
     * @param body
     * @return Call<Void>
     */

    @POST("todo")
    Call<Void> todoPost(
            @Body Todo body
    );


    /**
     * Get the specified todo
     *
     * @param id ID of todo
     * @return Call<Todo>
     */

    @GET("todo/{id}")
    Call<Todo> todoIdGet(
            @Path("id") Long id
    );


    /**
     * Update the specified todo
     *
     * @param id   ID of todo
     * @param body
     * @return Call<Void>
     */

    @PUT("todo/{id}")
    Call<Void> todoIdPut(
            @Path("id") Long id, @Body Todo body
    );


    /**
     * Delete the specified todo
     *
     * @param id ID of todo
     * @return Call<Void>
     */

    @DELETE("todo/{id}")
    Call<Void> todoIdDelete(
            @Path("id") Long id
    );


}
