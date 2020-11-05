package com.example.helloworld;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentPengumuman extends Fragment {

    RecyclerView recyclerView;
    String film[], desc[];
    int foto[] = {R.drawable.aac,R.drawable.aadc,R.drawable.sok};


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.recycleview);
        MyAdapterClass myAdapterClass = new MyAdapterClass(getActivity(), film,desc,foto);
        recyclerView.setAdapter(myAdapterClass);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        film = getResources().getStringArray(R.array.film);
        desc = getResources().getStringArray(R.array.film_desc);
        return inflater.inflate(R.layout.fragment_pengumuman,container,false);
    }
}
