package com.hendraanggrian.circularreveallayoutsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentRecyclerView extends Fragment {
    private View rootView;
    private Bundle bundle = new Bundle();

    private RecyclerViewAdapter adapter;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.button) Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, rootView);

        adapter = new RecyclerViewAdapter(rootView.getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("");
                recyclerView.scrollToPosition(adapter.getItemCount());
            }
        });

        return rootView;
    }
}