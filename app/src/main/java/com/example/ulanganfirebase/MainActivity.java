package com.example.ulanganfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lihatData(View view) {
        Intent lihatNotes = new Intent(MainActivity.this, DaftarNotes.class);
        startActivity(lihatNotes);
    }

    public void inputData(View view) {
        Intent tambahNotes = new Intent(MainActivity.this, InputData.class);
        startActivity(tambahNotes);
    }
}
