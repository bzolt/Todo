package balogh.zoltan.todo.ui;

import android.content.Context;


import javax.inject.Singleton;

import balogh.zoltan.todo.ui.main.MainPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

}