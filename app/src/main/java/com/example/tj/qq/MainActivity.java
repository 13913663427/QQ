package com.example.tj.qq;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);

        List<String> list = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
        List<SampleModel> dataList = new ArrayList<>();
        for (String str : list) {
            SampleModel model = new SampleModel(str);
            dataList.add(model);
        }
        Log.e("test", dataList.toString());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        SimpleAdapter adapter = new SimpleAdapter(this, dataList);
        recyclerView.setAdapter(adapter);
    }
}
