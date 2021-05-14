package com.example.demosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    DataStudent dataStudent;
    Button btnAdd,btnRemove,btnCancel;
    EditText name_edt;
    ListView lvName;

    ArrayList nameList;
    ArrayList idList;
    ArrayAdapter adapter;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataStudent=new DataStudent(this,"userdb",
                null,1);

        name_edt=findViewById(R.id.editTextTextPersonName);
        btnAdd=findViewById(R.id.btnAdd);
        btnRemove=findViewById(R.id.btnRemove);
        btnCancel=findViewById(R.id.btnCancel);
        lvName=findViewById(R.id.lvName);

        /*khoi tao nameList*/
        nameList=new ArrayList();
        idList=new ArrayList();

        getNameList();
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,nameList);
        lvName.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataStudent.addUser(new Student(name_edt.getText().toString()));
                getNameList();
                adapter.notifyDataSetChanged();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataStudent.RemoveUser((int)idList.get(index));
                getNameList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                index=i;
            }
        });


    }
    private  ArrayList getNameList(){
        nameList.clear();
        idList.clear();
        for (Iterator iterator = dataStudent.getAllUser().iterator(); iterator.hasNext(); ) {
            Student user = (Student) iterator.next();
            nameList.add(user.getName());
            idList.add(user.getId());

        }
        return  nameList;
    }
}