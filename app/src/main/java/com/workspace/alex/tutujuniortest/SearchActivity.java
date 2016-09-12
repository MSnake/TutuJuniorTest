package com.workspace.alex.tutujuniortest;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.workspace.alex.tutujuniortest.fragments.SearchFragment;
import com.workspace.alex.tutujuniortest.models.CityModel;

import java.util.ArrayList;

/**
 *
 * Активность поиска
 * Created by Alex on 12.09.2016.
 */
public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity"; // тег для автоматизированного тестирования
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Выводим фрагмент с выбором станций на экран
        fragmentManager = getFragmentManager();
        Log.d(TAG,"Количество транзакций в ФМ "+fragmentManager.getBackStackEntryCount());

        Intent i = getIntent();
        ArrayList<CityModel> data = (ArrayList<CityModel>) i.getSerializableExtra(SearchFragment.EXTRA_SEARCH_DATA);
        SearchFragment fragment = SearchFragment.newInstance(data);
        fragmentManager.beginTransaction().add(R.id.fragmentContainerSecond, fragment).commit();
        Log.d(TAG,"Количество транзакций в ФМ "+fragmentManager.getBackStackEntryCount());
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount()>0)
        {
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }
}
