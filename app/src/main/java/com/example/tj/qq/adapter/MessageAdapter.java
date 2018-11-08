package com.example.tj.qq.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tj.qq.R;
import com.example.tj.qq.item.MessageItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context context;
    private List<MessageItem> dataList;

    //创建Adapter并且获得context和datalist
    public MessageAdapter(Context context, List<MessageItem> dataList) {
        this.context = context;
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
        MessageItem messageItem = dataList.get(i);

        //绑定视图和item的具体某一条属性
        viewHolder.contentTV.setText(messageItem.getContent());

        // 长按字母，删除对应的行
        viewHolder.contentTV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dataList.remove(i);
                // 刷新adapter重新渲染界面
                notifyDataSetChanged();
                return false;
            }
        });

        //定义时间格式
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.CHINA);
        //将时间戳转换为String日期
        String time = format.format(new Date(messageItem.getTime()));
        //绑定视图和item中时间属性
        viewHolder.timeTV.setText(time);

        String url = messageItem.getImageUrl();
        Glide.with(context).load(url).into(viewHolder.imageTV);
    }


    @Override
    //获得数据源的大小
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView contentTV;
        public TextView timeTV;
        public TextView tittleTV;
        public ImageView imageTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //item中某条属性绑定到对应的控件
            contentTV = itemView.findViewById(R.id.tv_content);

            timeTV = itemView.findViewById(R.id.tv_time);
            imageTV = itemView.findViewById(R.id.tv_image);
            tittleTV = itemView.findViewById(R.id.tv_tittle);


        }
    }
}
