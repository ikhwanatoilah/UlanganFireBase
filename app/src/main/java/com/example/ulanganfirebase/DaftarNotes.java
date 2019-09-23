package com.example.ulanganfirebase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DaftarNotes extends AppCompatActivity implements View.OnClickListener,RecyclerviewAdapter.OnUserClickListener {

    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    Context context;
    List<Notes> listNotes;
    boolean test = true;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);


//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById (R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager (layoutManager);

        setupRecyclerView();

    }
    public void inputData(View view) {
        Intent formTambah = new Intent(DaftarNotes.this, InputData.class);
        startActivity(formTambah);
    }



    private void setupRecyclerView() {
        DatabaseHelper db = new DatabaseHelper(this);
        listNotes = db.selectUserData();

        RecyclerviewAdapter adapter = new RecyclerviewAdapter(this, listNotes,this);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnSimpan){
            inputData(v);
        }
    }

    @Override
    public void onUserClick(final Notes currentNotes) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih Menu");

        String[] isiDialog = {"Update Data", "Hapus Data"};
        builder.setItems(isiDialog, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Notes notes = new Notes();
                        notes.setJudul (currentNotes.getJudul ());
                        notes.setDeskripsi (currentNotes.getDeskripsi ());
                        Intent update = new Intent(DaftarNotes.this, InputData.class);
                        update.putExtra("Update","Update");
                        update.putExtra(InputData.EXTRA_PERSON, notes);
                        startActivity(update);
                        break;
                    case 1:
                        DatabaseHelper db=new DatabaseHelper(DaftarNotes.this);
                        db.delete(currentNotes.getJudul ());
                        db.delete(currentNotes.getDeskripsi ());
                        setupRecyclerView();
                        break;
                }
            }
        });

// create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }




}
