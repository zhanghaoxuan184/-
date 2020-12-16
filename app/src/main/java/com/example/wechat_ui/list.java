package com.example.wechat_ui;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class list extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    private String[] data = {"Apple", "B", "C", "D", "E", "F", "G", "Apple", "I", "B", "H", "N", "O", "U",
                             "Apple", "H", "Q", "V", "E", "we", "G", "adc", "lkj", "asd", "D", "L", "F", "wlj"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        initFruits();
        FruitAdapter adapter = new FruitAdapter(list.this, R.layout.fruit_item, fruitList);

        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }

    private void initFruits(){
        Fruit apple = new Fruit(data[0], R.drawable.picture);
        fruitList.add(apple);
        Fruit B = new Fruit(data[6], R.drawable.picture);
        fruitList.add(B);
        Fruit C = new Fruit("C", R.drawable.picture);
        fruitList.add(C);
        Fruit D = new Fruit("D", R.drawable.picture);
        fruitList.add(D);
        Fruit E = new Fruit("E", R.drawable.picture);
        fruitList.add(E);
        Fruit F = new Fruit("F", R.drawable.picture);
        fruitList.add(F);
        Fruit G = new Fruit("G", R.drawable.picture);
        fruitList.add(G);
        Fruit H = new Fruit("H", R.drawable.picture);
        fruitList.add(H);
    }
}
