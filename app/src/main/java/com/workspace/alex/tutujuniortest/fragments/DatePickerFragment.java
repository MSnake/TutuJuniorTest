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
 * Created by Alex on 12.09.2016.
 */
public class DatePickerFragment extends DialogFragment {
    private static final String TAG = "DatePickerFragment";
    public static final String EXTRA_DATE = "com.workspace.alex.tutujunior.date";
    private Date setUpDate;  //Устанавливаемая дата

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // определяем текущую дату
        Calendar c = Calendar.getInstance();
        //Добавляем 1 день к текущей дате
        c.add(Calendar.DATE, 1);
        //определяем год, месяц, день с учетом добавленного дня
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_date,null);
        DatePicker datePicker = (DatePicker) v.findViewById(R.id.dialog_date_datePicker);
        datePicker.setMinDate(c.getTimeInMillis());
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                setUpDate = new GregorianCalendar(year, month, day).getTime();
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

}
