package com.jw.calenderviewtest;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.OrientationHelper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    /*https://webnautes.tistory.com/1216*/
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        initViews();


    }

    private void initViews() {
        calendarView = findViewById(R.id.calendar_view);
        calendarView.setCalendarOrientation(OrientationHelper.HORIZONTAL);
        ((RadioGroup) findViewById(R.id.rg_selection_type)).setOnCheckedChangeListener(this);


//        calendarView.isShowDaysOfWeekTitle = false
//        calendarView.selectionManager = RangeSelectionManager(OnDaySelectedListener {
//            Log.e(" CALENDAR ", "========== setSelectionManager ==========")
//            Log.e(" CALENDAR ", "Selected Dates : " + calendar_view.selectedDates.size)
//            if (calendar_view.selectedDates.size <= 0) return@OnDaySelectedListener
//                    Log.e(" CALENDAR ", "Selected Days : " + calendar_view.selectedDays)
//        })
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.clear_selections:
                clearSelectionsMenuClick();
                return true;

            case R.id.show_selections:
                List<Calendar> days = calendarView.getSelectedDates();
                ArrayList<String> resultList = new ArrayList<>();
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < days.size() ; i++){

                    Calendar calendar = days.get(i);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int month = calendar.get(Calendar.MONTH);
                    int year = calendar.get(Calendar.YEAR);

//                    @SuppressLint("SimpleDateFormat")
//                    String week = new SimpleDateFormat("EE").format(calendar.getTime());
//                    String day_full = year +"년"+ (month+1)+"월"+day+"일"+week+"요일";

                    String day_full = year +"년"+ (month+1)+"월"+day+"일";
                    resultList.add(day_full);

                    result.append(day_full).append("\n");
                }
//                Toast.makeText(this , result.toString(),Toast.LENGTH_LONG).show();
                if (resultList.size() == 1){
                    Log.d("0521new" , "하나:"+resultList.get(0));
                }else{
                    Log.d("0521new" , "처음:"+resultList.get(0));
                    Log.d("0521new" , "끝:"+resultList.get(resultList.size()-1));
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clearSelectionsMenuClick() {
        calendarView.clearSelections();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        clearSelectionsMenuClick();
        switch (checkedId) {

            case R.id.rb_single:
                calendarView.setSelectionType(SelectionType.SINGLE);
                break;

            case R.id.rb_multiple:
                calendarView.setSelectionType(SelectionType.MULTIPLE);
                break;

            case R.id.rb_range:
                calendarView.setSelectionType(SelectionType.RANGE);
                break;

            case R.id.rb_none:
                calendarView.setSelectionType(SelectionType.NONE);
                break;
        }
    }
}