package com.example.tj.qq.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tj.qq.R;
import com.example.tj.qq.adapter.MessageAdapter;
import com.example.tj.qq.api.Api;
import com.example.tj.qq.api.NetWorkConstants;
import com.example.tj.qq.api.RecycleNetInterface;
import com.example.tj.qq.item.UserItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecycleNetActivity extends BaseActivity {
    private List<UserItem> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MessageAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void initView() {
        adapter = new MessageAdapter(this, dataList);
        //将Item组成的列表填充到Recycleview里面(绑定视图)
        recyclerView = findViewById(R.id.recycler_view);
        //设置RecycleView布局文件显示策略，LinearLayout线性垂直方向显示
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        Retrofit retrofit = Api.getRetrofit(NetWorkConstants.BASE_URL2);
        RecycleNetInterface recycleNetInterface = retrofit.create(RecycleNetInterface.class);
        Call<List<UserItem>> call = recycleNetInterface.getUsers();
        call.enqueue(new Callback<List<UserItem>>() {
            @Override
            public void onResponse(Call<List<UserItem>> call, Response<List<UserItem>> response) {
                List<UserItem> userItem = response.body();
                adapter.setDataList(userItem);
                adapter.notifyDataSetChanged();// 重新渲染界面
            }

            @Override
            public void onFailure(Call<List<UserItem>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    protected void initListener() {

    }
}
