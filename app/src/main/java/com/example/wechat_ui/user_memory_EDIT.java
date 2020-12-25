package com.example.wechat_ui;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 22308 on 2020-11-19.
 */

public class user_memory_EDIT extends AppCompatActivity {
    public static final int TAKE_PHOTO = 100;
    public static String name;
    public static final int CHOOSE_PHOTO = 200;

    private Button button_photo;

    private Button button_back;

    private ImageView imageViewPhoto;

    private EditText memory_Edit;
  /*  private static final String utl = "http://192.168.0.100:8080/server";
    public static WebView webViewEdit;*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_memory_edit);
        //       initWebView();
        button_photo = (Button)findViewById(R.id.user_memory_button_edit);
        imageViewPhoto = (ImageView)findViewById(R.id.user_memory_image_view);
        button_back = (Button)findViewById(R.id.user_memory_button_back);
        memory_Edit = (EditText)findViewById(R.id.user_memory_edit_text);

        Intent intent = getIntent();
        final int position = intent.getIntExtra("userposition",-1);
        final String memory = intent.getStringExtra("usermemory");
        name= intent.getStringExtra("username");

        if( !TextUtils.isEmpty(memory) ){
            memory_Edit.setText(memory);
        }

        button_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = memory_Edit.getText().toString();
                if(TextUtils.isEmpty(message) ){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(user_memory_EDIT.this);
                    dialog.setTitle("确认退出?");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                }
                else{
                    Intent intent = new Intent();
                  /*  intent.putExtra("memory",message);
                    int imageId = -1;
                    if( imageViewPhoto.getDrawable()!=null){
                        imageId = imageViewPhoto.getId();
                    }*/

                    insert();//保存至数据库
                    //    intent.putExtra("imageID",imageId);
                    intent.putExtra("memory",message);
                    if( position!=-1 ){
                        intent.putExtra("position",position);
                    }
                    setResult(1,intent); //返回数据给上一个活动
                    finish();
                }
              /*  AlertDialog.Builder dialog = new AlertDialog.Builder(MemoryEdit.this);
                dialog.setTitle("确认放弃")*/
              /*  new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_VIEW;
                        handler.sendMessage(message);
                    }
                }).start();*/
            }
        });
    }
/*    public void initWebView()
    {
        webViewEdit = (WebView)findViewById(R.id.edit_memory_webview_edit);
        WebSettings settings = webViewEdit.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        webViewEdit.setWebViewClient(new WebViewClient());
        try{
            webViewEdit.loadUrl("file:///android_asset/edittext.html");
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    private void openAlbum(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode)
        {
            case CHOOSE_PHOTO:
                if( data!=null)
                {
                    //获取数据
                    //获取内容解析者对象
                    try {
                        Bitmap mBitmap = BitmapFactory.decodeStream(
                                getContentResolver().openInputStream(data.getData()));
                        imageViewPhoto.setImageBitmap(mBitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    /*  public Handler handler = new Handler(){
          public void handleMessage(Message msg){
              switch (msg.what){
                  case UPDATE_VIEW:
                    //  addView();
                      String message = memory_Edit.getText().toString();

                      break;
              }
          }
      };*/
 /*   public void addView() {
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.memory_edit_layout);
        linearLayout.removeView(webViewEdit);
        // 获取当前的时间并转换为时间戳格式, 并设置给TextView
        String currentTime = dateToStamp(System.currentTimeMillis());
        // 调用一个参数的addView方法
        MemoryRead.container.addView(webViewEdit);
    }*/
    public String dateToStamp(long s) {
        String res;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(s);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            return "";
        }
        return res;
    }
 /* public byte[] img(int id)
  {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(id)).getBitmap();
      bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
      return baos.toByteArray();
  }*/

    public long insert()
    {
        SQLiteDatabase db = user_memory_READ.addtbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("memory",memory_Edit.getText().toString());
        //  cv.put("imageId",imageViewPhoto.getId());
        cv.put("time",dateToStamp(System.currentTimeMillis()));
        long result = db.insert("user", null, cv);
        db.close();
        return result;
    }

 /*   public Bitmap getBmp(int position)
    {
        SQLiteDatabase db = MemoryRead.dbHelper.getReadableDatabase();
        Cursor cursor = db.query("memory_item",null,null,null,null,null,null);
        cursor.moveToPosition(position);
        byte[] in = cursor.getBlob(cursor.getColumnIndex("image"));
        Bitmap bmp_out = BitmapFactory.decodeByteArray(in, 0, in.length);
        return bmp_out;
    }*/
 /*   public Drawable chage_to_drawable(Bitmap bp)
    {
        //因为BtimapDrawable是Drawable的子类，最终直接使用bd对象即可。
        Bitmap bm=bp;
        BitmapDrawable bd= new BitmapDrawable(getResource(), bm);
        return bd;
    }*/
}
