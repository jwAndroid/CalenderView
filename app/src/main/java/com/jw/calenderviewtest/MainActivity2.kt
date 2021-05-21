package com.jw.calenderviewtest

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.OrientationHelper
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import com.applikeysolutions.cosmocalendar.utils.SelectionType
import com.applikeysolutions.cosmocalendar.view.CalendarView
import java.util.*

class MainActivity2 : AppCompatActivity() , RadioGroup.OnCheckedChangeListener {

    private lateinit var valcalendarView2 : CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(findViewById(R.id.toolbar))
        initViews()

    }

    private fun initViews() {
        valcalendarView2 = findViewById(R.id.calendar_view2)
        valcalendarView2.calendarOrientation = OrientationHelper.HORIZONTAL
        (findViewById<View>(R.id.rg_selection_type) as RadioGroup).setOnCheckedChangeListener(this@MainActivity2)

        valcalendarView2.isShowDaysOfWeek = false
        valcalendarView2.selectionManager = RangeSelectionManager(OnDaySelectedListener {
            Log.d(" CALENDAR ", "========== setSelectionManager ==========")
            Log.d(" CALENDAR ", "Selected Dates : " + valcalendarView2.selectedDates.size)
            if (valcalendarView2.selectedDates.size <= 0) return@OnDaySelectedListener
            Log.d(" CALENDAR ", "Selected Days : " + valcalendarView2.selectedDays)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.menu , menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.clear_selections -> {
                clearSelectionsMenuClick()
            }

            R.id.show_selections -> {
                val days: List<Calendar> = valcalendarView2.selectedDates
                val resultList = ArrayList<String>()
                val result = StringBuilder()
                var i = 0
                while (i < days.size) {
                    val calendar = days[i]
                    val day = calendar[Calendar.DAY_OF_MONTH]
                    val month = calendar[Calendar.MONTH]
                    val year = calendar[Calendar.YEAR]

                    val day_full = year.toString() + "년" + (month + 1) + "월" + day + "일"
                    resultList.add(day_full)
                    result.append(day_full).append("\n")
                    i++
                }

                if (resultList.size == 1) {
                    Log.d("0521new", "하나:" + resultList[0])

                } else {
                    Log.d("0521new", "처음:" + resultList[0])
                    Log.d("0521new", "끝:" + resultList[resultList.size - 1])
                }
            }

            else -> super.onOptionsItemSelected(item)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun clearSelectionsMenuClick() {
        valcalendarView2.clearSelections()
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        clearSelectionsMenuClick()
        when (checkedId) {
            R.id.rb_single -> valcalendarView2.selectionType = SelectionType.SINGLE
            R.id.rb_multiple -> valcalendarView2.selectionType = SelectionType.MULTIPLE
            R.id.rb_range -> {
                valcalendarView2.selectionType = SelectionType.RANGE
                valcalendarView2.selectionManager = RangeSelectionManager(OnDaySelectedListener {
                    Log.d(" CALENDAR ", "========== setSelectionManager ==========")
                    Log.d(" CALENDAR ", "Selected Dates : " + valcalendarView2.selectedDates.size)
                    if (valcalendarView2.selectedDates.size <= 0) return@OnDaySelectedListener
                    Log.d(" CALENDAR ", "Selected Days : " + valcalendarView2.selectedDays)
                })
            }

            R.id.rb_none -> valcalendarView2.selectionType = SelectionType.NONE
        }
    }
}