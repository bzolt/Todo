package balogh.zoltan.todo;

import android.app.Application;

import balogh.zoltan.todo.ui.UIModule;

public class TodoApplication extends Application {

	public static TodoApplicationComponent injector;

	@Override
	public void onCreate() {
		super.onCreate();

		injector =
				DaggerTodoApplicationComponent.builder().
						uIModule(
								new UIModule(this)
						).build();
	}
}