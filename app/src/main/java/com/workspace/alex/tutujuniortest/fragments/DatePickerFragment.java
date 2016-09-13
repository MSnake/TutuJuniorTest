package com.workspace.alex.tutujuniortest.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.workspace.alex.tutujuniortest.R;
import com.workspace.alex.tutujuniortest.models.TripModel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Фрагмент выбоа даты поездки
 * Created by Alex on 12.09.2016.
 */
public class DatePickerFragment extends DialogFragment {
    private static final String TAG = "DatePickerFragment";  //logCat тэг
    public static final String EXTRA_DATE = "com.workspace.alex.tutujunior.date"; //ключ для хранения даты
    private Date setUpDate;  //Устанавливаемая дата

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        setUpDate  = (Date) getArguments().getSerializable(EXTRA_DATE);
        // определяем текущую дату

        Calendar nextDay = Calendar.getInstance();
        //Добавляем 1 день к текущей дате
        nextDay.add(Calendar.DATE, 1);

        Calendar c = Calendar.getInstance();
        //Добавляем 1 день к текущей дате
        c.add(Calendar.DATE, 1);
        //Если даата была выбрана ранее проверяем больше ли она завтрашнего дня
        // если больше то отталкиваемся в дальнейшем от нее
        if (setUpDate.getTime() > c.getTime().getTime())
        {
            c.setTime(setUpDate);
        }

        //определяем год, месяц, день с учетом добавленного дня
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_date,null);
        DatePicker datePicker = (DatePicker) v.findViewById(R.id.dialog_date_datePicker);
        datePicker.setMinDate(nextDay.getTimeInMillis());
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                setUpDate = new GregorianCalendar(year, month, day).getTime();
                Log.d(TAG,"дата изменена" );
            }
        });
        return new android.support.v7.app.AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.choose_date).
                setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }

    //Отправляем результаты
    private void sendResult(int resultCode)
    {
        Log.d(TAG,"Отправка резульатов");
        if (getTargetFragment()==null)
        {
            return;
        }

        Intent i = new Intent();
        i.putExtra(EXTRA_DATE, setUpDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);

    }

    //Создание фрагмента с установленной датой в аргементе
    public static DatePickerFragment newInstance(Date date)
    {
        Log.d(TAG,"Создание фрагмента "+TAG );
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE,date);
        DatePickerFragment dP = new DatePickerFragment();
        dP.setArguments(args);
        return dP;

    }

}
