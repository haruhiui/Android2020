package com.bytedance.todolist.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.bytedance.todolist.R;
import com.bytedance.todolist.database.TodoListDao;
import com.bytedance.todolist.database.TodoListDatabase;
import com.bytedance.todolist.database.TodoListEntity;

import java.util.Date;

public class TodoListTakeNoteActivity extends AppCompatActivity {
    private EditText contentView;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_take_note_layout);
        contentView = findViewById(R.id.text_content);
        confirmBtn = findViewById(R.id.btn_confirm);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        TodoListDao dao = TodoListDatabase.inst(TodoListTakeNoteActivity.this).todoListDao();
                        dao.addTodo(new TodoListEntity(contentView.getText().toString(), new Date(System.currentTimeMillis())));
                    }
                }.start();
                finish();
            }
        });
    }
}
