package com.example.wechat_ui;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by 22308 on 2020-11-20.
 */

public class Memory {

    private String memory;

    //  private Bitmap bitmap;

    public Memory(String memory)
    {
        this.memory= memory;
        // this.bitmap = bitmap;
    }

    public String getMemory(){
        return memory;
    }

    public void setMemory(String memory){
        this.memory = memory;
    }

  /*  public Bitmap getBitmap(){
        return bitmap;
    }*/
}
