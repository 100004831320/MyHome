package com.example.administrator.smarthome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.smarthome.Datas.UserDevice;

import java.util.List;

/**
 * Created by Administrator on 2021/4/19 0019.
 */

public class Myadapter extends ArrayAdapter<UserDevice> {
    private int resourceId;
    public Myadapter(Context context, int textViewResourceId, List<UserDevice> objects ){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LinearLayout listView;
        UserDevice device = getItem(position); //获取当前项的Fruit实例

        String serialnumber = String.valueOf(device.getserialnumber());
        String temperature = String.valueOf(device.gettemperature());
        String humidity = String.valueOf(device.gethumidity());
        String SmokeConcentration = String.valueOf(device.getSmokeConcentration());
        // 加个判断，以免ListView每次滚动时都要重新加载布局，以提高运行效率
        View view;
        RecyclerView.ViewHolder viewHolder;
        if (convertView==null){

            listView = new LinearLayout(getContext());
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(resourceId, listView, true);
            // 避免ListView每次滚动时都要重新加载布局，以提高运行效率
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

           /* // 避免每次调用getView()时都要重新获取控件实例
            viewHolder = new RecyclerView.ViewHolder(UserDevice);
            viewHolder.getItemId() = view.findViewById(R.id.Name_list);
            viewHolder.fruitName=view.findViewById(R.id.fruit_name);

            // 将ViewHolder存储在View中（即将控件的实例存储在其中）
            view.setTag(viewHolder);*/
        } else{
            listView = (LinearLayout)convertView;
        }
        TextView Name_list = listView.findViewById(R.id.Name_list);
        TextView Temperature = listView.findViewById(R.id.Temperature);
        TextView Humidity = listView.findViewById(R.id.Humidity);
        TextView Smoke_concentration = listView.findViewById(R.id.Smoke_concentration);

        Name_list.setText("设备号："+serialnumber);
        Temperature.setText("温度："+temperature);
        Humidity.setText("湿度："+humidity);
        Smoke_concentration.setText("烟雾浓度："+SmokeConcentration);

        // 获取控件实例，并调用set...方法使其显示出来
       /* viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;*/
       return listView;
    }

}
