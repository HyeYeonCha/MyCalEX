package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    CompactCalendarView compactCalendarView;
    private SimpleDateFormat dataFormatMonth = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    SimpleDateFormat clickday = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");
    SimpleDateFormat clickMonth = new SimpleDateFormat("yyyy년 MM월");

    TextView month;
    TextView selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        month = findViewById(R.id.month);
        selectDate = findViewById(R.id.selectDate);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.setUseThreeLetterAbbreviation(true);





        // 이벤트 추가 >> ex 2019년 April 5일 Friday AM 9:33:35
        Event ev1 = new Event(Color.RED, 1554456815000L, "Event Day !!!" ); // 이벤트 >> 오늘 날짜 https://www.epochconverter.com/ 에서 milliseconds 받아오기
        compactCalendarView.addEvent(ev1);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                Date cDay = new Date();

                if(dateClicked.toString().compareTo("Fri 05 Apr 2019 09:33:35 GMT 2019") == 0) {
                    Toast.makeText(context, "Event Day !!!", Toast.LENGTH_SHORT).show();
                    month.setText(clickMonth.format(cDay));
                    selectDate.setText(clickday.format(cDay));
                } else {
                    Toast.makeText(context, "NO Event Day..", Toast.LENGTH_SHORT).show();
                    month.setText(clickMonth.format(cDay));
                    selectDate.setText(clickday.format(cDay));
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dataFormatMonth.format(firstDayOfNewMonth));
            }
        });


    }
}
