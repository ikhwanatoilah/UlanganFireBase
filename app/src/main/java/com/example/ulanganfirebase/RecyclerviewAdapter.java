package com.example.ulanganfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.UserViewHolder> {
    Context context;
    OnUserClickListener listener;

    List<Notes> listNotes;
    public RecyclerviewAdapter(Context context, List<Notes> listNotes, OnUserClickListener listener) {
        this.context=context;
        this.listNotes = listNotes;
        this.listener=listener;
    }


    public interface OnUserClickListener {

        void onUserClick(Notes currentNotes);

    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data,parent,false);
        UserViewHolder userViewHolder=new UserViewHolder(view);

        return userViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final Notes currentOrang = listNotes.get (position);
        holder.judul.setText (currentOrang.getJudul ());
        holder.deskripsi.setText (currentOrang.getDeskripsi ());


        holder.crdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentOrang);
            }
        });

    }
    @Override
    public int getItemCount() {
        return listNotes.size();

    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        CardView crdv;
        TextView judul,deskripsi;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            crdv = itemView.findViewById (R.id.card);
            judul = itemView.findViewById (R.id.tvJudul);
            deskripsi = itemView.findViewById (R.id.tvDeskripsi);
        }
    }

}
