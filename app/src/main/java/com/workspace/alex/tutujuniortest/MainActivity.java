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
import android.view.View;

import com.workspace.alex.tutujuniortest.data.ArrivelData;
import com.workspace.alex.tutujuniortest.data.DepartureData;
import com.workspace.alex.tutujuniortest.fragments.AboutFragment;
import com.workspace.alex.tutujuniortest.fragments.TimingFragment;
import com.workspace.alex.tutujuniortest.models.CityModel;
import com.workspace.alex.tutujuniortest.models.StationModel;
import com.workspace.alex.tutujuniortest.models.TripModel;
import com.workspace.alex.tutujuniortest.utils.JSONDataLoad;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "MainActivity"; // тег для автоматизированного тестирования
    private static final String ARRIVEL_DATA="arrivelData"; //метка для сохранения и восстоановления данных о станциях отправления
    private static final String DEPARTURE_DATA="departureData"; //метка для сохранения и восстоановления данных о станциях прибытия
    private static final String TRIP_DATA_DATE="tripDataDate"; //метка для сохранения и восстоановления данных о дате поездки
    private static final String TRIP_DATA_FROM="tripDataFrom"; //метка для сохранения и восстоановления данных о пункте отправления поездки
    private static final String TRIP_DATA_TO="tripDataTo"; //метка для сохранения и восстоановления данных о пункте назначения поездки
    private FragmentManager fragmentManager;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateState(savedInstanceState);
        //Выводим фрагмент с выбором станций на экран
        fragmentManager = getFragmentManager();
        Log.d(TAG,"Количество транзакций в ФМ "+fragmentManager.getBackStackEntryCount());

        TimingFragment fragment = new TimingFragment();
        fragmentManager.beginTransaction().add(R.id.fragmentContainerFirst, fragment).commit();
        Log.d(TAG,"Количество транзакций в ФМ "+fragmentManager.getBackStackEntryCount());

        initNavigationBars();

        //Играем с изменение иконок тулбара в зависимости от заполненности fragment manager
//        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            onBackPressed();
//                            int fragmentBackStackCount = fragmentManager.getBackStackEntryCount();
//                            while ( fragmentBackStackCount> 0){
//                                fragmentManager.popBackStack();
//                                fragmentBackStackCount--;
//                            }
//                        }
//                    });
//                } else {
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                    toggle.syncState();
//                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            drawer.openDrawer(GravityCompat.START);
//                        }
//                    });
//                }
//            }
//        });

    }


    /**
     * Инициализация навигации
     */
    private void initNavigationBars()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setEnabled(true);

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "Сохранение состояния(подгруженной базы со станциями)");
        outState.putSerializable(ARRIVEL_DATA, ArrivelData.getInstance().getData());
        outState.putSerializable(DEPARTURE_DATA, DepartureData.getInstance().getData());
        Log.d(TAG, "Сохранение состояния(информации о поездке)");
        outState.putSerializable(TRIP_DATA_DATE, TripModel.getInstance().getDateOfTrip());
        outState.putSerializable(TRIP_DATA_FROM, TripModel.getInstance().getFromStation());
        outState.putSerializable(TRIP_DATA_TO, TripModel.getInstance().getToStation());

    }

    /**
     * Обновление состояний
     * @param savedInstanceState
     */
    private void updateState(Bundle savedInstanceState)
    {
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

            //Считывание данных о хранилищах данных
            ArrayList<CityModel> arrMod = (ArrayList<CityModel>) savedInstanceState.getSerializable(ARRIVEL_DATA);
            ArrivelData.getInstance().setData(arrMod);
            ArrayList<CityModel> depMod = (ArrayList<CityModel>) savedInstanceState.getSerializable(DEPARTURE_DATA);
            //Считывание данных о поездке
            DepartureData.getInstance().setData(depMod);
            Date dateTrip = (Date)savedInstanceState.getSerializable(TRIP_DATA_DATE);
            StationModel fromModel = (StationModel)savedInstanceState.getSerializable(TRIP_DATA_FROM);
            StationModel toModel = (StationModel)savedInstanceState.getSerializable(TRIP_DATA_TO);
            TripModel.getInstance().setDateOfTrip(dateTrip);
            TripModel.getInstance().setFromStation(fromModel);
            TripModel.getInstance().setToStation(toModel);
        }
        Log.d(TAG, "Кол-во элементов в датах: отправление "+ArrivelData.getInstance().getData().size()+" приыбытие"+DepartureData.getInstance().getData().size());
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Действие при нажатии на пункт меню
        Log.d(TAG, "Нажатие сработало");
        fragmentManager = getFragmentManager();
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_timing) {
            fragment = new TimingFragment();
        } else
        if (id == R.id.nav_about)
        {

            fragment = new AboutFragment();

        }
        Log.d(TAG,"Количество транзакций в ФМ "+fragmentManager.getBackStackEntryCount());
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerFirst, fragment).addToBackStack(null).commit();

        Log.d(TAG,"Количество транзакций в ФМ "+fragmentManager.getBackStackEntryCount());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
