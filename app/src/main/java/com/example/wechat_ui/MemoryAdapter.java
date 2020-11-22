package com.example.wechat_ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 22308 on 2020-11-20.
 */

public class MemoryAdapter extends ArrayAdapter<Memory>{

    private int resourceId;

    public MemoryAdapter(Context context, int textViewResourceId, List<Memory> objects)
    {
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        Memory memory = getItem(position); //滑倒当前位置 获取其实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView memoryImage = (ImageView)view.findViewById(R.id.memory_image);
        TextView memoryName = (TextView)view.findViewById(R.id.memory_name);
        memoryImage.setImageResource(memory.getImageId());
        memoryName.setText(memory.getName());
        return view;
    }
}
