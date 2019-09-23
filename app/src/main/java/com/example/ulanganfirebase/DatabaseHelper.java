package com.example.ulanganfirebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;
    private static final String DB_NAME="Notes";
    private static final String TABLE_NAME="tbl_notes";
    private static final String KEY_JUDUL="judul";
    private static final String KEY_DESKRIPSI="deskripsi";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable="Create Table " +TABLE_NAME+"("+KEY_JUDUL+" TEXT,"+KEY_DESKRIPSI+" TEXT"+")";
        db.execSQL(createUserTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql=("drop table if exists " +TABLE_NAME);
        db.execSQL (sql);
        onCreate (db);
    }

    public void insert (Notes notes){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(KEY_JUDUL, notes.getJudul ());
        values.put(KEY_DESKRIPSI, notes.getDeskripsi());
        db.insert (TABLE_NAME,null,values);
    }

    public List<Notes> selectUserData(){
        ArrayList<Notes> userList= new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase ();
        String[] columns ={KEY_DESKRIPSI,KEY_JUDUL};

        Cursor c =db.query (TABLE_NAME,columns,null,null,null,null,null);
        while (c.moveToNext ()){

            String judul=c.getString (0);
            String deskripsi=c.getString (1);


            Notes notes = new Notes();
            notes.setJudul (judul);
            notes.setDeskripsi (deskripsi);
            userList.add(notes);

        }
        return userList;
    }
    public void delete(String judul){
        SQLiteDatabase db = getWritableDatabase ();
        String WhereClause = KEY_JUDUL+"='"+judul+"'";
        db.delete (TABLE_NAME,WhereClause,null);
    }
    public void update(Notes notes){
        SQLiteDatabase db = getReadableDatabase ();
        ContentValues values = new ContentValues();
        values.put (KEY_JUDUL, notes.getJudul ());
        values.put (KEY_DESKRIPSI, notes.getDeskripsi ());
        String whereClause = KEY_JUDUL+"='"+ notes.getJudul ()+"'";
        db.update (TABLE_NAME,values,whereClause,null);
    }


}
