package com.example.wechat_ui;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class HomeFragment extends Fragment {
    private RadioButton radio_bj;
    private RadioButton radio_sh;
    private Button submit;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.page_01, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        //单选按钮组
        submit= (Button) Objects.requireNonNull(getActivity()).findViewById(R.id.submit);
        //单选按钮组
        radio_bj = (RadioButton) Objects.requireNonNull(getActivity()).findViewById(R.id.radio_bj);
        radio_sh = (RadioButton)Objects.requireNonNull(getActivity()).findViewById(R.id.radio_sh);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //监听器要做的事情
                Intent i = new Intent(getActivity(),weather.class);
                if(radio_bj.isChecked()){
                    i.putExtra("data", "101010100");
                    startActivity(i);
                }
                if(radio_sh.isChecked()){
                    i.putExtra("data", "101020100");
                    startActivity(i);
                }

            }
        });

    }
}
