package com.example.helloworld;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapterClass extends RecyclerView.Adapter<MyAdapterClass.MyViewHolder>  {

    String film[], desc[];
    int foto[];
    Context context;

    public MyAdapterClass(Context context, String s1[], String s2[], int img[]){
        this.context = context;
        this.film = s1;
        this.desc = s2;
        this.foto = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_film, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.movieTitle.setText(film[i]);
        myViewHolder.movieDesc.setText(desc[i]);
        myViewHolder.moviePoster.setImageResource(foto[i]);
    }

    @Override
    public int getItemCount() {
        return film.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle, movieDesc;
        ImageView moviePoster;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.txtFilm);
            movieDesc = itemView.findViewById(R.id.txtDesc);
            moviePoster = itemView.findViewById(R.id.foto);
        }
    }
}
