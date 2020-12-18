package com.example.wechat_ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;

public class MemoryRead extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static ListView container;
    public static final int UPDATE_VIEW = 101;
    public static final int CHANGE_VIEW = 102;
    MemoryAdapter adapter;
    private List<Memory> memoryList = new ArrayList<>();
    private ImageButton imageButton;
    static Bitmap bitmap = null;
    @SuppressLint("StaticFieldLeak")
    static editDatabaseHelper dbHelper;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handlerMessage(Message msg){
            switch (msg.what){
                case UPDATE_VIEW:
                    String memory = (String) msg.obj;
                    if(!memory.equals("")){
                        Memory m = new Memory(memory);
                        adapter.add(m);
                    }
                    break;
                case CHANGE_VIEW:
                    memory = (String)msg.obj;
                    int position = msg.arg1;
                    if( !memory.equals("")&&position!=-1 ){
                        adapter.setMemory(memory,position);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_read);

        dbHelper = new editDatabaseHelper(this,"memory_item",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        adapter = new MemoryAdapter(MemoryRead.this,R.layout.memory_item,memoryList);//适配器
        container = (ListView)findViewById(R.id.memory_read_container);
        container.setAdapter(adapter);

        /*
         在此遍历所有数据库
         */
        Cursor cursor = db.query("memory_item",null,null,null,null,null,null);
        if( cursor.moveToFirst() )
        {
            do{
                String memory = cursor.getString(cursor.getColumnIndex("memory"));
                Memory m = new Memory(memory);
                adapter.add(m);
            }while(cursor.moveToNext());
            cursor.close();
        }

        imageButton = (ImageButton)findViewById(R.id.read_button_to_edit);
        imageButton.setOnClickListener(new View.OnClickListener(){ ///////提交活动
            public void onClick(View view)
            {
                Intent intent = new Intent(MemoryRead.this, MemoryEdit.class);
                startActivityForResult(intent,UPDATE_VIEW);
            }
        });

        container.setOnItemClickListener(new AdapterView.OnItemClickListener() { //点击监听器
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Memory memory = memoryList.get(position);
                Intent intent = new Intent(MemoryRead.this,MemoryEdit.class);
                intent.putExtra("memory",memory.getMemory());
                intent.putExtra("position",position);
                /*    bitmap = memory.getBitmap();*/
                //    startActivityForResult(intent,CHANGE_VIEW);
                startActivity(intent);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    { ///得到返回的活动
        switch (requestCode){
            case UPDATE_VIEW:
                if( resultCode==1 ){
                   /* String returnMemory = data.getStringExtra("memory");
                    int id = data.getIntExtra("imageId",-1);
                    Memory memory = new Memory(returnMemory,id);
                    memoryList.add(memory);*/
                  /*  SQLiteDatabase db = dbHelper.getWritableDatabase();
                    Cursor cursor = db.query("memory_item",null,null,null,null,null,null);
                    if( cursor.moveToLast() ){
                        String memory = cursor.getString(cursor.getColumnIndex("memory"));
                        Memory m = new Memory(memory);
                        memoryList.add(m);
                    }*/
                    String read = "";
                    read = data.getStringExtra("memory"); //得到返回的数据
                    final String finalRead = read;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.what = UPDATE_VIEW;
                            message.obj = finalRead;
                            handler.sendMessage(message);
                        }
                    }).start();
                }
                break;
            case CHANGE_VIEW:
                final int position = data.getIntExtra("position",-1);
                final String memory = data.getStringExtra("memory");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = CHANGE_VIEW;
                        message.arg1 = position;
                        message.obj = memory;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
        }
    }
}
