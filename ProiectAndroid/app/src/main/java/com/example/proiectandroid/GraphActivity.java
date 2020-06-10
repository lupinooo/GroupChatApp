package com.example.proiectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {
    AnyChartView anyChartView;
    int[] earnings={500, 200, 100};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Intent intent=getIntent();
        String myUsername=intent.getExtras().getString("myUsername");
        UsersDB usersDB=UsersDB.getInstance(this);

        anyChartView=findViewById(R.id.pieChart);
        String[] friendsCountries=usersDB.getFriendsDAO().getfriendsCountries(myUsername);
        int [] friendsNumber=new int[friendsCountries.length];
        int j=0;
        for(int i=0;i<friendsCountries.length;i++){
            int no=usersDB.getFriendsDAO().friendsFromCountry(friendsCountries[i]);
            friendsNumber[j]=no;
            j++;

        }

        Pie pieChart= AnyChart.pie();
        List<DataEntry> data=new ArrayList<>();
        for(int i=0;i<friendsCountries.length;i++){
            data.add(new ValueDataEntry(friendsCountries[i], friendsNumber[i]));
        }
        pieChart.data(data);
        anyChartView.setChart(pieChart);

    }
}
