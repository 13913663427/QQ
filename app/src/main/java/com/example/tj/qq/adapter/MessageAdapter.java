package com.example.tj.qq.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.tj.qq.R;
import com.example.tj.qq.item.UserItem;

import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context context;
    private List<UserItem> dataList;

    //创建Adapter并且获得context和datalist
    public MessageAdapter(Context context, List<UserItem> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setDataList(List<UserItem> dataList) {
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //将XML布局转换为View
        View itemView = View.inflate(context, R.layout.item_message_view, null);
        //让ViewHolder拥有这个视图
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //获得第i条item
        UserItem userItem = dataList.get(i);

        //绑定视图和item的具体某一条属性
        viewHolder.nameTV.setText(userItem.getUserName());

        viewHolder.ageTV.setText(String.valueOf(userItem.getAge()));


        //绑定视图和item中时间属性
        viewHolder.sexTV.setText(userItem.getSex());

        String url = userItem.getHeadUrl();
        Glide.with(context).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.imageTV);


        // 长按字母，删除对应的行
        viewHolder.nameTV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dataList.remove(i);
                // 刷新adapter重新渲染界面
                notifyDataSetChanged();
                return false;
            }
        });
    }


    @Override
    //获得数据源的大小
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTV;
        TextView ageTV;
        TextView sexTV;
        ImageView imageTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            //item中某条属性绑定到对应的控件
            nameTV = itemView.findViewById(R.id.tv_name);
            ageTV = itemView.findViewById(R.id.tv_age);
            sexTV = itemView.findViewById(R.id.tv_sex);
            imageTV = itemView.findViewById(R.id.tv_image);


        }
    }
}
