package com.jw.calenderviewtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class MainActivity3 extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        compareData(3);
        compareData(4);
        compareData(5);

    }

    private void compareData(int input_data) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        for (int num : arr) {
            if (num == input_data)
                Log.d("num :: " ,"f"+ num );
                return;
        }

    }
}