//package com.example.tj.qq.activity;
//
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//
//import com.alibaba.fastjson.JSON;
//import com.example.tj.qq.R;
//import com.example.tj.qq.adapter.MessageAdapter;
//import com.example.tj.qq.item.UserItem;
//import com.lzy.okgo.OkGo;
//import com.lzy.okgo.callback.StringCallback;
//import com.lzy.okgo.model.Response;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RecycleNetActivity extends BaseActivity {
//    private List<UserItem> dataList = new ArrayList<>();
//    private RecyclerView recyclerView;
//    private MessageAdapter adapter;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_recycler_view;
//    }
//
//    @Override
//    protected void initView() {
//        adapter = new MessageAdapter(this, dataList);
//        //将Item组成的列表填充到Recycleview里面(绑定视图)
//        recyclerView = findViewById(R.id.recycler_view);
//        //设置RecycleView布局文件显示策略，LinearLayout线性垂直方向显示
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//    }
//
//    @Override
//    protected void initData() {
//
//        OkGo.<String>get("http://www.wanandroid.com/tools/mockapi/2452/getUser")
//                .execute(new StringCallback() {
//            @Override
//            public void onSuccess(Response<String> response) {
//                String jsonstr = response.body();
//                adapter.setDataList(JSON.parseArray(jsonstr, UserItem.class));
//                adapter.notifyDataSetChanged();// 重新渲染界面
//
//
//            }
//        });
//
//    }
//
//    @Override
//    protected void initListener() {
//
//    }
//}
