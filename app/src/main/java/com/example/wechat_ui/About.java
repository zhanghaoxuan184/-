package com.example.wechat_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class About extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        Button button = (Button) Objects.requireNonNull(About.this).findViewById(R.id.about_back);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(About.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }


}
