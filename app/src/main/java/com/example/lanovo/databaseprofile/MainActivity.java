package com.example.lanovo.databaseprofile;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.lanovo.databaseprofile.R.attr.title;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText et, et2, et3, et4;
    Button btn, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        et = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        Adddata();
        getdata();
        update();
        deletedata();
    }
    public void Adddata() {
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = mydb.insertData(et2.getText().toString(),et3.getText().toString(),et4.getText().toString());
                if(isInserted == true)
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Please Try Again Latter", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void getdata() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.getalldata();
                if (res.getCount() == 0) {
                    showMessage("Error","Not this Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("\n"+"Id:" + res.getString(0) + "\n");
                    buffer.append("Name:" + res.getString(1) + "\t");
                    buffer.append("Surname:" + res.getString(2) + "\t");
                    buffer.append("Profession:" + res.getString(3) + "\t");
                }
                showMessage("Data",buffer.toString());
            }
        });
    }
        public void showMessage(String title,String Message){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();

    }
    public void update() {
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = mydb.updateData(et.getText().toString(), et2.getText().toString(),
                        et3.getText().toString(), et4.getText().toString());
                if (isUpdate == true)
                    Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Please Try Again Latter", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void deletedata() {
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean deletedraw = mydb.deleteData(et.getText().toString());
                if (deletedraw == true)
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Please Try Again Latter", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
