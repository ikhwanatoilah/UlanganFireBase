package com.example.ulanganfirebase;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes implements Parcelable {
    String deskripsi,judul,tanggal;

    public Notes() {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getJudul() {
        return judul;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString (this.judul);
        dest.writeString (this.deskripsi);
    }

    protected Notes(Parcel in) {
        this.judul = in.readString ();
        this.deskripsi = in.readString ();
    }

    public static final Parcelable.Creator<Notes> CREATOR = new Parcelable.Creator<Notes> () {
        @Override
        public Notes createFromParcel(Parcel source) {
            return new Notes(source);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

}
