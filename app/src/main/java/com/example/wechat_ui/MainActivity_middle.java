package com.example.wechat_ui;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_middle extends AppCompatActivity {

    private List<Memory> fruitList = new ArrayList<>();

    private String[] data = {"Apple", "B", "C", "D", "E", "F", "G", "Apple", "I", "B", "H", "N", "O", "U",
                             "Apple", "H", "Q", "V", "E", "we", "G", "adc", "lkj", "asd", "D", "L", "F", "wlj"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();
        MemoryAdapter adapter = new  MemoryAdapter(MainActivity_middle.this, R.layout.fruit_item, fruitList);

        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }

    private void initFruits(){
        for (int i = 0; i < 2; i++){
            Memory apple = new Memory("Apple", R.drawable.tab_address);
            fruitList.add(apple);
            Memory B = new Memory("B", R.drawable.tab_address);
            fruitList.add(B);
            Memory C = new Memory("C", R.drawable.tab_address);
            fruitList.add(C);
            Memory D = new Memory("D", R.drawable.tab_address);
            fruitList.add(D);
            Memory E = new Memory("E", R.drawable.tab_address);
            fruitList.add(E);
            Memory F = new Memory("F", R.drawable.tab_address);
            fruitList.add(F);
            Memory G = new Memory("G", R.drawable.tab_address);
            fruitList.add(G);
            Memory H = new Memory("H", R.drawable.tab_address);
            fruitList.add(H);
        }
    }
}
