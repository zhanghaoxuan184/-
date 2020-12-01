package com.example.wechat_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserinfoAcitvity extends AppCompatActivity {

    private RadioGroup radioGroupSex;
    private Button buttonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_edit);

        radioGroupSex = (RadioGroup)findViewById(R.id.radio_user_sex);
        buttonback=(Button)findViewById(R.id.user_edit_back);

        buttonback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserinfoAcitvity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        radioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup radioGroup,int i){
                RadioButton radioButton = (RadioButton)findViewById(i);
                Toast.makeText(UserinfoAcitvity.this,"你的性别是:"+radioButton.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }


}
