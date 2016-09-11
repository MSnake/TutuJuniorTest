package com.workspace.alex.tutujuniortest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.workspace.alex.tutujuniortest.R;
import com.workspace.alex.tutujuniortest.models.StationModel;

import java.io.Serializable;


/**
 * Фрагмент содержащий детальную информацию о выбранной станции
 * Created by Alex on 11.09.2016.
 */
public class StationInfoFragment extends Fragment {

    public static final String EXTRA_STATION_INFO="com.workspace.alex.tutujunior.stationinfofragment";

    private StationModel station;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments().getSerializable(EXTRA_STATION_INFO) == null)
        {
            station = new StationModel();
        }
        else
        {
            station = (StationModel) getArguments().getSerializable(EXTRA_STATION_INFO);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_station_info, container, false);
        TextView stationName = (TextView) v.findViewById(R.id.info_station_name_textView);
        TextView stationCity = (TextView) v.findViewById(R.id.info_station_city_textView);
        TextView stationCountry = (TextView) v.findViewById(R.id.info_station_country_textView);
        TextView stationRegion = (TextView) v.findViewById(R.id.info_station_region_textView);

        stationName.setText(station.getStation());
        stationCity.setText(station.getCity());
        stationCountry.setText(station.getCountry());
        stationRegion.setText(station.getRegion());

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static StationInfoFragment newInstance(StationModel model)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_STATION_INFO, model);
        StationInfoFragment fragment = new StationInfoFragment();
        fragment.setArguments(args);
        return fragment;

    }
}
