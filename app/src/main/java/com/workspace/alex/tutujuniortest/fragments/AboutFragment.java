package com.workspace.alex.tutujuniortest.fragments;

import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.workspace.alex.tutujuniortest.R;

/**
 * Created by Alex on 10.09.2016.
 */
public class AboutFragment extends Fragment {

    private TextView versionTextView; //view для отображения версии проекта

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about,container,false);
        versionTextView = (TextView) v.findViewById(R.id.about_version);
        String versionName = null;
        try {
            //Достаем версия из манифеста проекта
            versionName = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versionTextView.setText("Версия: "+versionName);
        return v;
    }
}
