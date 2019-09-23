package com.example.ulanganfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class InputData extends AppCompatActivity {
    public static String EXTRA_PERSON = " extra_person";
    public static String ACTION = "Insert";

    protected OnBackPressedListener onBackPressedListener;

    public interface OnBackPressedListener {
        void doBack();
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null)
            onBackPressedListener.doBack();
        else
            super.onBackPressed();
    }

    EditText judul,deskripsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_data);


//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeButtonEnabled(false);
//        actionBar.setDisplayHomeAsUpEnabled(true);

        deskripsi = findViewById (R.id.deskripsi);
        judul = findViewById (R.id.judul);


        if(getIntent().hasExtra("Update")) {
            Notes notes = getIntent ().getParcelableExtra (EXTRA_PERSON);
            judul.setText (notes.getJudul ()+"");
            deskripsi.setText (notes.getDeskripsi ()+"");
        }



    }

    public void simpanData(View view) {
        DatabaseHelper db = new DatabaseHelper(this);
        Notes currentData = new Notes();

        if(!getIntent().hasExtra("Update")) {
            currentData.setDeskripsi (judul.getText().toString ());
            currentData.setJudul (deskripsi.getText().toString ());
            db.insert(currentData);
        }else{
            currentData.setJudul (judul.getText().toString ());
            currentData.setDeskripsi (deskripsi.getText().toString ());
            db.update(currentData);
        }
        Intent simpanData = new Intent(InputData.this, MainActivity.class);
        startActivity(simpanData);
    }
}
