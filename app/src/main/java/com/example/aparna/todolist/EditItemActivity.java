package com.example.aparna.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {
    public String editTitle;
    public EditText editTextView;
    Integer position;
    ArrayList<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        items = getIntent().getStringArrayListExtra("allItems");
        position = getIntent().getIntExtra("position", 0);
        editTextView = (EditText)findViewById(R.id.editText);
        editTextView.setText(title);
        editTextView.setSelection(title.length());
    }
    public  void onSave(View v) {
        Intent data = new Intent();
        String editedText = editTextView.getText().toString();
        items.set(position, editedText);
        writeItems();
        setResult(RESULT_OK, data);
        finish();
    }
    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
