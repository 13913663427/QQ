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

        //获取RecycleView对象
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        //创建线性布局管理器（默认垂直方向）
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //为RecycleView指定布局管理对象
        recyclerView.setLayoutManager(layoutManager);

        //创建Adapter对象
        SimpleAdapter adapter = new SimpleAdapter(this, dataList);
        //将对象与控件相关联
        recyclerView.setAdapter(adapter);
    }
}
