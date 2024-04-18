package com.example.taskchecker;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText taskInput;
    Button addButton, deleteButton;
    ListView taskList;
    ArrayAdapter<String> adapter;
    ArrayList<String> tasks;


    private int selectedItemPosition = ListView.INVALID_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = findViewById(R.id.taskInput);
        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteButton);
        taskList = findViewById(R.id.taskList);

        tasks = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        taskList.setAdapter(adapter);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = taskInput.getText().toString();
                if (!task.isEmpty()) {
                    tasks.add(task);
                    adapter.notifyDataSetChanged();
                    taskInput.setText("");
                }
            }
        });

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Update selected item position
                selectedItemPosition = position;
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if any item is selected
                if (selectedItemPosition != ListView.INVALID_POSITION) {
                    // Remove the selected item from the list
                    tasks.remove(selectedItemPosition);

                    // Notify the adapter that the dataset has changed
                    adapter.notifyDataSetChanged();

                    // Clear the selection
                    taskList.clearChoices();

                    // Reset selected item position
                    selectedItemPosition = ListView.INVALID_POSITION;
                }
            }
        });

    }

}
