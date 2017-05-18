package balogh.zoltan.todo.mock;

import java.io.IOException;

import javax.inject.Singleton;

import balogh.zoltan.todo.network.NetworkModule;
import balogh.zoltan.todo.network.api.TodoApi;
import balogh.zoltan.todo.network.api.UserApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

@Module
public class MockNetworkModule {
    private NetworkModule networkModule = new NetworkModule();

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return networkModule.provideOkHttpClientBuilder();
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {

        builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                return MockHttpServer.call(request);
            }
        });

        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return networkModule.provideRetrofit(client);
    }

    @Provides
    @Singleton
    public TodoApi provideTodoApi(Retrofit retrofit) {
        return networkModule.provideTodoApi(retrofit);
    }

    @Provides
    @Singleton
    public UserApi provideUserApi(Retrofit retrofit) {
        return networkModule.provideUserApi(retrofit);
    }
}