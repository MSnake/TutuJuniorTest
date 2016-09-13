package com.workspace.alex.tutujuniortest.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.workspace.alex.tutujuniortest.R;
import com.workspace.alex.tutujuniortest.data.DepartureData;
import com.workspace.alex.tutujuniortest.models.CityModel;
import com.workspace.alex.tutujuniortest.models.StationModel;

import java.util.ArrayList;

/**
 * Фрагмент поиска нужного пункта
 * Created by Alex on 11.09.2016.
 */
public class SearchFragment extends Fragment {


    private static final String TAG = "SearchFragment"; //Тег для logcat
    public static final String EXTRA_SEARCH_DATA="com.workspace.alex.tutujunior.searchfragment"; //ключ для контейнера с массивом данных для поиска
    public static final String EXTRA_FOUND_ITEM="com.workspace.alex.tutujunior.founditem"; //ключ для контейнера с выбранным результатом
    private ListView list;              //view для отображения результатов поиска станции
    private CityAdapter adapter;        //адаптер со списком станкий
    private ArrayList<StationModel> allStations;    //массив со всеми станциями (прибытия или отправления)
    private FragmentManager fragmentManager;

    // Search EditText
    private EditText inputSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ArrayList<CityModel> data;
        if (getArguments().getSerializable(EXTRA_SEARCH_DATA) == null)
        {
            data = new ArrayList<>();
        }
        else
        {
            data = (ArrayList<CityModel>) getArguments().getSerializable(EXTRA_SEARCH_DATA);
        }

        createStationsList(data);
        adapter = new CityAdapter(allStations);
    }


    //Возобновление фрагмента
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"Вызван onResume");
        //Обновляем адаптер
        adapter.notifyDataSetChanged();

    }



    /**
     * Метод формирования списка всех станций из всех городов
     * @param citys - массив городов содержащих информацию о станциях
     */
    private void createStationsList(ArrayList<CityModel> citys)
    {
        allStations = new ArrayList<>();
        for (CityModel city: citys) {
            for (StationModel station : city.getStations()) {
                StationModel newStation = station;
                String cityNameStation = newStation.getCity().replaceFirst("город ","");
                newStation.setCity(cityNameStation);
                allStations.add(newStation);
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        createStationsList(DepartureData.getInstance().getData());
        ArrayList<StationModel> adapterList = (ArrayList<StationModel>) allStations.clone();
        adapter = new CityAdapter(adapterList);
        list = (ListView) v.findViewById(R.id.listView);
        inputSearch = (EditText) v.findViewById(R.id.inputSearch);
        inputSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                    inputSearch.setHint("");
                else
                    inputSearch.setHint(R.string.enter_station_name);
            }
        });
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                updateList(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                StationModel s = adapter.getItem(position);
                Log.d(TAG,s.getStation()+ " нажата");
                StationInfoFragment infoFragment = StationInfoFragment.newInstance(s);
                fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerSecond, infoFragment).addToBackStack(null).commit();
                return true;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                StationModel s = adapter.getItem(position);
                Log.d(TAG,s.getStation()+ " нажата");
                Intent data = new Intent();
                data.putExtra(EXTRA_FOUND_ITEM, s);
                getActivity().setResult(Activity.RESULT_OK, data);
                getActivity().finish();
            }


        });
        return v;
    }


    //Оновляем список городов в адаптере
    private void updateList(String inputText) {
        ArrayList<StationModel> foundStation = new ArrayList<StationModel>();
        String enterText = inputText.toLowerCase().trim();
        Log.d(TAG, "Обновление списка на данный момент: " + adapter.getCount() + "элементов");
        adapter.clear();
        Log.d(TAG, "Очистка адаптера теперь: " + adapter.getCount() + "элементов");
        //Если ничего не ввели
        if (enterText.equals("")) {
            for (StationModel station : allStations) {
                foundStation.add(station);
            }
            Log.d(TAG, "Пустое поле ввода в адаптере " + adapter.getCount() + "элементов");
        } else {
            for (StationModel station : allStations) {
                String ctName = station.getCity().toLowerCase().trim();
                if (ctName.contains(enterText)) {
                    foundStation.add(station);
                }
                Log.d(TAG, "Что то ввели в адаптере " + adapter.getCount() + "элементов");
            }
        }
        adapter.addAll(foundStation);
        //Обновляемся
        adapter.notifyDataSetChanged();

    }



    //Создание фрагмента с установленым хранилищем данных
    public static SearchFragment newInstance(ArrayList<CityModel> model)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SEARCH_DATA, model);
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;

    }


    //Собственный адаптер для отображения переданных станций
    private class CityAdapter extends ArrayAdapter<StationModel> {

        public CityAdapter(ArrayList<StationModel> stations) {
            super(getActivity(), 0, stations);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Если мы не получили представление, заполняем его;
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_fragment, null);
            }

            StationModel s = getItem(position);
            TextView ctyName = (TextView) convertView.findViewById(R.id.item_cityName_textView);
            ctyName.setText(s.getCity());
            TextView cntryName = (TextView) convertView.findViewById(R.id.item_country_textView);
            cntryName.setText(s.getCountry());
            TextView sttnName = (TextView) convertView.findViewById(R.id.item_station_name_textView);
            sttnName.setText(s.getStation());

            return convertView;
        }
    }
}
