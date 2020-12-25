package com.example.wechat_ui;

public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name, int imageId){
        this.imageId = imageId;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

    public void setName(String name){ this.name=name;}

    public void setImageId(int imageId){
        this.imageId = imageId;
    }
}
