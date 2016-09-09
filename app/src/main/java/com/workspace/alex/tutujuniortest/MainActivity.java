package com.workspace.alex.tutujuniortest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.workspace.alex.tutujuniortest.utils.JSONDataLoad;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String IOTAG = "mainActivityIO";
    private static final String JSONTAG = "mainActivityJSON";

    private final  String  filename = "fromStationSmall.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button initBtn = (Button) findViewById(R.id.init_button);
        initBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Выполняем инициализацию хранилищ данных
                try {
                    JSONDataLoad.initDatas(getApplicationContext());
                    Toast.makeText(getApplicationContext(),"Загружено",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Log.d(IOTAG, "IO Exeption " + e.toString());
                } catch (JSONException e) {
                    Log.d(IOTAG, "JSON Exeption " + e.toString());
                }

            }
        });

    }
}
