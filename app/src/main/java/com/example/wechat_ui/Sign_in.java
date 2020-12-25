package com.example.wechat_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sign_in extends AppCompatActivity {
    Button registration;
    Button sign_in;
    EditText username;
    EditText code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        registration=(Button)findViewById(R.id.registration);
        sign_in=(Button)findViewById(R.id.sign_in);
        username=(EditText)findViewById(R.id.sign_in_user_name);
        code=(EditText)findViewById(R.id.sign_in_code);


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1;
                String s2;
                String s3;
                String s4;
                s1=username.getText().toString();
                s2=code.getText().toString();
                s4=s1+s2;
                s3=load();
                if (s4.equals(s4)){
                    Intent intent = new Intent(Sign_in.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Sign_in.this, "账号或密码输入错误，请修改之后再登录！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sign_in.this, registration.class);
                startActivity(intent);
            }
        });
    }
    public String load(){
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try{
            in=openFileInput("data");
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while((line=reader.readLine())!=null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

}
