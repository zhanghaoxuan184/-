package com.example.wechat_ui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class UserinfoAcitvity extends AppCompatActivity {

    private RadioGroup radioGroupSex;
    private Button buttonback;
    private Button button;
    private TextView name;
    private EditText age;

    public void insert(String name,String age){
        Student helper = new Student(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("age",age);
        long id = db.insert("stu",null,values);
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_edit);

        //radioGroupSex = (RadioGroup)findViewById(R.id.radio_user_sex);
        buttonback=(Button)findViewById(R.id.user_edit_back);
        button = (Button)findViewById(R.id.button);
        name = (TextView)findViewById(R.id.e_name);
        age = (EditText) findViewById(R.id.e_age);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                insert(name.getText().toString(),age.getText().toString());
                Toast.makeText(UserinfoAcitvity.this,"添加成功！",Toast.LENGTH_SHORT).show();
            }
        });




    buttonback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserinfoAcitvity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //radioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
        //    public void onCheckedChanged(RadioGroup radioGroup,int i){
        //        RadioButton radioButton = (RadioButton)findViewById(i);
        //        Toast.makeText(UserinfoAcitvity.this,"你的性别是:"+radioButton.getText().toString(),Toast.LENGTH_SHORT).show();
        //    }
        //});
    }


}