package com.workspace.alex.tutujuniortest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.workspace.alex.tutujuniortest.data.ArrivelData;
import com.workspace.alex.tutujuniortest.data.DepartureData;
import com.workspace.alex.tutujuniortest.fragments.AboutFragment;
import com.workspace.alex.tutujuniortest.fragments.TimingFragment;
import com.workspace.alex.tutujuniortest.models.CityModel;
import com.workspace.alex.tutujuniortest.utils.JSONDataLoad;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "MainActivity"; // тег для автоматизированного тестирования
    private static final String ARRIVEL_DATA="arrivelData"; //метка для сохранения и восстоановления данных о станциях отправления
    private static final String DEPARTURE_DATA="departureData"; //метка для сохранения и восстоановления данных о станциях прибытия

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            // при первом запуске программы
            try {
                //Производим загрузку данных из JSON в случае если
                // хранилища данных пусты
                if (ArrivelData.getInstance().getData().isEmpty())
                {

                    JSONDataLoad.initDatas(getApplicationContext());
                    Log.d(TAG, "Инициализация станций прибытия и отправления прошла успешно.");
                }
                //Выводим фрагмент с выбором станций на экран
                Fragment fragment = new TimingFragment();
                FragmentManager fragmentManager = getFragmentManager();
//                fragment = fragmentManager.findFragmentById(R.id.fragmentContainerFirst);
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerFirst, fragment).commit();

            } catch (IOException e) {
                Log.d(TAG, "Файл с настройками не найден");
            } catch (JSONException e) {
                Log.d(TAG, "JSON файл не обработан");
            }
        }
        else
        {
            Log.d(TAG, "Загрузка данных из сохраненного состояния");
            //Считывание данных если они ранее были загружены
            ArrayList<CityModel> arrMod = (ArrayList<CityModel>) savedInstanceState.getSerializable(ARRIVEL_DATA);
            ArrivelData.getInstance().setData(arrMod);
            ArrayList<CityModel> depMod = (ArrayList<CityModel>) savedInstanceState.getSerializable(DEPARTURE_DATA);
            DepartureData.getInstance().setData(depMod);
        }

        Log.d(TAG, "Кол-во элементов в датах: отправление "+ArrivelData.getInstance().getData().size()+" приыбытие"+DepartureData.getInstance().getData().size());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setEnabled(true);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "Сохранение состояния");
        outState.putSerializable(ARRIVEL_DATA, ArrivelData.getInstance().getData());
        outState.putSerializable(DEPARTURE_DATA, DepartureData.getInstance().getData());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }






    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Действие при нажатии на пункт меню
        Log.d(TAG, "Нажатие сработало");
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_timing) {
            fragment = new TimingFragment();
        } else
        if (id == R.id.nav_about)
        {
            fragment= new AboutFragment();

        }



        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
//            fragment = fragmentManager.findFragmentById(R.id.fragmentContainerFirst);
            fragmentManager.beginTransaction().replace(R.id.fragmentContainerFirst, fragment).commit();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            Log.d(TAG, "Новый фрагмент успешно добавлен");

        } else {

            Log.d(TAG, "Не удалость отобразить фрагмент");
        }

        return true;
    }
}
