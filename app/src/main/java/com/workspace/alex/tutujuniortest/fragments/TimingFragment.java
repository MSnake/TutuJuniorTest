package com.workspace.alex.tutujuniortest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.workspace.alex.tutujuniortest.R;

/**
 * Фрагмент отображения интерфейса расписания
 * Created by Alex on 10.09.2016.
 */
public class TimingFragment extends Fragment {

    private TextView departureTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_timing,container,false);
        departureTitle = (TextView) v.findViewById(R.id.departure_station_name);
        departureTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Здесь по нажатию будет открываться новый фрагмент и возможностью поиска по названию города
            }
        });
        return v;

    }
}

