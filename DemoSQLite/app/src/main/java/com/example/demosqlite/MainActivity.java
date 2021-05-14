package com.example.demosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    DataStudent dataStudent;
    Button add_btn,remove_btn;
    EditText name_edt;
    ListView lvName;
    ArrayList nameList;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataStudent = new DataStudent(this, "student.sqlite", null, 1);
        dataStudent.addStudent(new Student("Thanh"));
        dataStudent.addStudent(new Student("Huy "));
        dataStudent.addStudent(new Student("Toan"));
        dataStudent.addStudent(new Student("Hung"));


        nameList = new ArrayList();

        name_edt = findViewById(R.id.editTextTextPersonName);
        add_btn = findViewById(R.id.btnAdd);
        remove_btn = findViewById(R.id.btnRemove);
        lvName = findViewById(R.id.lvName);


        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dataStudent.addStudent(new Student(name_edt.getText().toString()));
                    getNameList();
                    arrayAdapter.notifyDataSetChanged();
            }
        });
    //   getNameList();
        arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,nameList);
            lvName.setAdapter(arrayAdapter);
    }

    private ArrayList getNameList(){

        nameList.clear();

        for (Iterator iterator = dataStudent.getAll().iterator(); iterator.hasNext(); ) {
            Student student = (Student) iterator.next();

            nameList.add(student.getName());

            
        }
        return nameList;
    }

}