package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todoapp.data.Todo;

public class TodoAdapter extends ListAdapter<Todo, TodoAdapter.TodoViewHolder> {

    private OnTodoActionListener listener;

    public interface OnTodoActionListener {
        void onTodoClick(Todo todo);
        void onTodoDelete(Todo todo);
        void  onTodoEdit(Todo todo);
    }

    protected TodoAdapter() {
        super(new DiffUtil.ItemCallback<Todo>() {
            @Override public boolean areItemsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
                return oldItem.id == newItem.id;
            }
            @Override public boolean areContentsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
                return oldItem.title.equals(newItem.title) && oldItem.completed == newItem.completed;
            }
        });
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = getItem(position);
        holder.tvTitle.setText(todo.title);
        holder.cbCompleted.setChecked(todo.completed);
        holder.cbCompleted.setOnClickListener(v -> {
            todo.completed = holder.cbCompleted.isChecked();
            if (listener != null) listener.onTodoClick(todo);
        });


        holder.cbCompleted.setOnClickListener(v -> {
            todo.completed = holder.cbCompleted.isChecked();
            if (listener != null) listener.onTodoClick(todo);
        });

        holder.itemView.setOnClickListener(v -> {
            // Modifier la tâche
            if (listener != null) listener.onTodoEdit(todo);
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) listener.onTodoDelete(todo);
            return true;
        });




    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        CheckBox cbCompleted;

        TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            cbCompleted = itemView.findViewById(R.id.cbCompleted);
        }
    }

    public void setOnTodoActionListener(OnTodoActionListener listener) {
        this.listener = listener;
    }
}


