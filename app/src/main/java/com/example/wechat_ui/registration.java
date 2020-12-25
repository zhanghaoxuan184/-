package com.example.wechat_ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 2020/12/12.
 */

public class registration extends AppCompatActivity {
    Button registration_finish;
    EditText user_name;
    EditText code;
    EditText second_code;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        registration_finish=(Button)findViewById(R.id.registration_finish);
        user_name=(EditText)findViewById(R.id.user_name);
        code=(EditText)findViewById(R.id.code);
        second_code=(EditText)findViewById(R.id.second_code);




        registration_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String x1;
                String x2;
                String x3;
                String x4;
                x1=user_name.getText().toString();
                x2=code.getText().toString();
                x3=second_code.getText().toString();
                if(!x2.equals(x3))
                {
                    Toast.makeText(registration.this,"两次密码输入不一致，请重新输入！",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    x4=x1+x2;
                    save(x4);
                    finish();
                }
            }
        });
    }
    /*@Override
    protected void onDestroy(){
        super.onDestroy();
        String x1=user_name.getText().toString();
        String x2=code.getText().toString();
        save(x1);
        save(x2);
    }*/
    public void save(String inputText){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try {
            out=openFileOutput("data", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer !=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
