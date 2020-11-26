package com.example.wechat_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class MemoryRead extends AppCompatActivity {
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_03);

        imageButton = (ImageButton) findViewById(R.id.read_button_to_edit);
        imageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //跳转页面
                Intent intent = new Intent(MemoryRead.this, MemoryEdit.class);
                startActivity(intent);

                return false;
            }

        });


    }

}