package com.example.wechat_ui;

/**
 * Created by 22308 on 2020-11-20.
 */

public class Memory {

    private String name;

    private int imageId;

    public Memory(String name, int imageId)
    {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }
}
