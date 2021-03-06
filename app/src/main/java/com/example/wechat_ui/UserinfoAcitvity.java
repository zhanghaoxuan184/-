package com.example.wechat_ui;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.wechat_ui.MainActivity;
import com.example.wechat_ui.R;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserinfoAcitvity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 100;
    public static final int CHOOSE_PHOTO = 200;
    private Button buttonback;
    private Button button_photo;
    private EditText user_Edit;
    private ImageView imageViewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_edit);

        button_photo = (Button)findViewById(R.id.user_picture_edit);
        buttonback = (Button) findViewById(R.id.user_edit_back);
        user_Edit = (EditText) findViewById(R.id.e_name);
        imageViewPhoto = (ImageView)findViewById(R.id.title_image_view_photo);

        Intent intent = getIntent();
        final int position = intent.getIntExtra("position", -1);
        final String memory = intent.getStringExtra("memory");
        if (!TextUtils.isEmpty(memory)) {
            user_Edit.setText(memory);
        }



        button_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
            }
        });

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = user_Edit.getText().toString();
                if (TextUtils.isEmpty(message)) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(UserinfoAcitvity.this);
                    dialog.setTitle("?????????????");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    dialog.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                }
                else {
                    Intent intent = new Intent();

                    insert();//??????????????????
                    intent.putExtra("memory", message);
                    if (position != -1) {
                        intent.putExtra("position", position);
                    }
                    setResult(1, intent); //??????????????????????????????
                    finish();
                }
            }
        });


    }

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
                    //????????????
                    //???????????????????????????
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
    public long insert() {
        SQLiteDatabase db = list.dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("memory", user_Edit.getText().toString());
        //  cv.put("imageId",imageViewPhoto.getId());
        cv.put("time", dateToStamp(System.currentTimeMillis()));
        long result = db.insert("fruit_item", null, cv);
        return result;
    }

}