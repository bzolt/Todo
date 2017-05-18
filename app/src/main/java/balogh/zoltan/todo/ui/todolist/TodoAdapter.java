package balogh.zoltan.todo.ui.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import balogh.zoltan.todo.R;
import balogh.zoltan.todo.model.Todo;

public class TodoAdapter extends BaseAdapter {
    List<Todo> todos;
    CompoundButton.OnCheckedChangeListener todoCheckListener;
    View.OnClickListener todoDetailsListener;
    View.OnClickListener todoEditListener;
    View.OnClickListener todoDeleteListener;

    public TodoAdapter(List<Todo> todos, CompoundButton.OnCheckedChangeListener todoCheckListener, View.OnClickListener todoDetailsListener, View.OnClickListener todoEditListener, View.OnClickListener todoDeleteListener) {
        this.todos = todos;
        this.todoCheckListener = todoCheckListener;
        this.todoDetailsListener = todoDetailsListener;
        this.todoEditListener = todoEditListener;
        this.todoDeleteListener = todoDeleteListener;
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int position) {
        return todos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return todos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            layout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_todo, parent, false);
//            layout.setLayoutParams(new GridView.LayoutParams(85, 85));
            layout.setPadding(8, 8, 8, 8);
        } else {
            layout = (LinearLayout) convertView;
        }

        Todo todo = todos.get(position);
        layout.setTag(todo.getId());
        layout.setOnClickListener(todoDetailsListener);
        TextView title = (TextView) layout.findViewById(R.id.title);
        title.setText(todo.getTitle());
        TextView content = (TextView) layout.findViewById(R.id.content);
        content.setText(todo.getContent());
        CheckBox done = (CheckBox) layout.findViewById(R.id.done);
        done.setChecked(todo.isDone());
        done.setTag(todo);
        done.setOnCheckedChangeListener(todoCheckListener);
        Button edit = (Button) layout.findViewById(R.id.edit);
        edit.setTag(todo.getId());
        edit.setOnClickListener(todoEditListener);
        Button delete = (Button) layout.findViewById(R.id.delete);
        delete.setTag(todo);
        delete.setOnClickListener(todoDeleteListener);

        return layout;

    }
}
