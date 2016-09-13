package com.workspace.alex.tutujuniortest.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.workspace.alex.tutujuniortest.R;
import com.workspace.alex.tutujuniortest.SearchActivity;
import com.workspace.alex.tutujuniortest.data.DepartureData;
import com.workspace.alex.tutujuniortest.models.StationModel;
import com.workspace.alex.tutujuniortest.models.TripModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Фрагмент отображения интерфейса расписания
 * Created by Alex on 10.09.2016.
 */
public class TimingFragment extends Fragment {

    private static final String TAG = "TimingFragment"; //Тег для logCat
    private static final String DIALOG_DATE="date";
    private static final int REQUEST_DEPARTUTE_STATION = 0; //Код для запроса выбора станции отправления
    private static final int REQUEST_ARRIVEL_STATION = 1;   //Код для запроса выбора станции прибытия
    public static final int REQUEST_DATE = 2;          //Код для запроса выбора даты отправления
    private TextView departureTitle;    //виджет станции отправления
    private TextView arrivelTitle;      //виджет станции прибытия
    private EditText dateEditText;      //виджет даты отправления


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Вызов метода onCreate фрагмента");
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_timing,container,false);
        Log.d(TAG, "Создание представления фрагмента");
        //Инициализация полей с выбором станций отправления и прибытия
        departureTitle = (TextView) v.findViewById(R.id.departure_station_name);
        arrivelTitle = (TextView) v.findViewById(R.id.arrivel_station_name);
        dateEditText = (EditText) v.findViewById(R.id.date_pick_editText);

        Log.d(TAG, "Виджеты фрагмента инициализированы");
        //Обновление view
        updateView();

        departureTitle.setFocusable(false);
        //Обрабатка клика по полю станции отправления
        departureTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "departureTitle нажат");
                Intent i = new Intent(getActivity(),SearchActivity.class);
                i.putExtra(SearchFragment.EXTRA_SEARCH_DATA,DepartureData.getInstance().getData());
                startActivityForResult(i,REQUEST_DEPARTUTE_STATION);
            }
        });


        arrivelTitle.setFocusable(false);
        //Обрабатка клика по полю станции прибытия
        arrivelTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "arrivelTitle нажат");
                Intent i = new Intent(getActivity(),SearchActivity.class);
                i.putExtra(SearchFragment.EXTRA_SEARCH_DATA,DepartureData.getInstance().getData());
                startActivityForResult(i,REQUEST_ARRIVEL_STATION);

            }
        });


        dateEditText.setFocusable(false);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Выбор даты нажат");
                FragmentManager fm = getActivity().getFragmentManager();
                DatePickerFragment dp = DatePickerFragment.newInstance(TripModel.getInstance().getDateOfTrip());
                dp.setTargetFragment(TimingFragment.this,REQUEST_DATE);
                dp.show(fm,DIALOG_DATE);

            }
        });

        return v;
    }


    /**
     * Метод обновления информации на View
     */
    private void updateView()
    {
        Log.d(TAG, "Обновление представления TimingFragment");
        String text="";                                                 //полный текст в поле станции
        String hint = getText(R.string.enter_station_name).toString();    //hint (если нужен)

        //Обновляем editText c пунктом отправления
        String sationStr  = TripModel.getInstance().getFromStation().getStation();
        if (sationStr.length()>0)
        {
            text = TripModel.getInstance().getFromStation().getCity()+", "+
                    sationStr ;
            hint = "";
        }
        departureTitle.setText(text);
        departureTitle.setHint(hint);


        //Обновляем editText c пунктом прибытия
        hint = getText(R.string.enter_station_name).toString();
        text = "";
        sationStr = TripModel.getInstance().getToStation().getStation();
        if (sationStr.length()>0)
        {
            text = TripModel.getInstance().getToStation().getCity()+", "+
                    sationStr ;
            hint = "";
        }
        arrivelTitle.setText(text);
        arrivelTitle.setHint(hint);

        //Обновляем editText с датой поездки
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy");
        Date d = TripModel.getInstance().getDateOfTrip();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 0);
        hint = getText(R.string.choose_date).toString();
        String dateString ="";
        if (d.getTime() >= c.getTime().getTime())
        {
            hint = "";
            dateString = sdf.format(TripModel.getInstance().getDateOfTrip());
        }
        dateEditText.setText(dateString);
        dateEditText.setHint(hint);

        Log.d(TAG, "Обновление представления TimingFragment прошло успешно");
    }

    /**
     * Метод получения данных из вызванных активностей
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "Получаем ответ от с кодом "+requestCode);
        if (data != null)
        {
            Log.d(TAG, "Интент не пустой");
            StationModel currentStation;
            switch (requestCode)
            {
                case REQUEST_DEPARTUTE_STATION:
                    currentStation = (StationModel) data.getSerializableExtra(SearchFragment.EXTRA_FOUND_ITEM);
                    //Обновляем модель поездки
                    TripModel.getInstance().setFromStation(currentStation);
                    Log.d(TAG, "Информация о станции отправления изменена");
                    break;
                case REQUEST_ARRIVEL_STATION:
                    currentStation = (StationModel) data.getSerializableExtra(SearchFragment.EXTRA_FOUND_ITEM);
                    //Обновляем модель поездки
                    TripModel.getInstance().setToStation(currentStation);
                    Log.d(TAG, "Информация о прибытия изменена");
                    break;
                case REQUEST_DATE:
                    Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
                    TripModel.getInstance().setDateOfTrip(date);
                    Log.d(TAG, "Информация о дате поездки изменена");
                    break;
            }
            updateView();
        }


    }



}

