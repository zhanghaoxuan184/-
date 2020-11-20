package com.example.wechat_ui;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_middle extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    private String[] data = {"Apple", "B", "C", "D", "E", "F", "G", "Apple", "I", "B", "H", "N", "O", "U",
                             "Apple", "H", "Q", "V", "E", "we", "G", "adc", "lkj", "asd", "D", "L", "F", "wlj"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity_middle.this, R.layout.fruit_item, fruitList);

        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }

    private void initFruits(){
        for (int i = 0; i < 2; i++){
            Fruit apple = new Fruit("Apple", R.drawable.tab_address);
            fruitList.add(apple);
            Fruit B = new Fruit("B", R.drawable.tab_address);
            fruitList.add(B);
            Fruit C = new Fruit("C", R.drawable.tab_address);
            fruitList.add(C);
            Fruit D = new Fruit("D", R.drawable.tab_address);
            fruitList.add(D);
            Fruit E = new Fruit("E", R.drawable.tab_address);
            fruitList.add(E);
            Fruit F = new Fruit("F", R.drawable.tab_address);
            fruitList.add(F);
            Fruit G = new Fruit("G", R.drawable.tab_address);
            fruitList.add(G);
            Fruit H = new Fruit("H", R.drawable.tab_address);
            fruitList.add(H);
        }
    }
}
