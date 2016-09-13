package com.workspace.alex.tutujuniortest;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

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
        Log.d(TAG,"Создание активности "+TAG);
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


    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //При нажатии кнопки home(стрелка назад)
            case android.R.id.home:
                Log.d(TAG,"Нажали назад в приложении" );
                //Смотри бэк стэк фрагментов, если есть возвращаемся на 1, если нет,
                //значит мы в корне стека фрагментов --> завершить активность
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG,"Нажали назад аппапартно" );
        if (getFragmentManager().getBackStackEntryCount()>0)
        {
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }
}
