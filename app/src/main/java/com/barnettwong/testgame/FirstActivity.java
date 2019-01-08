package com.barnettwong.testgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by wang on 2019/1/5 16:36
 */
public class FirstActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);

        OnBtnClickListener onBtnClickListener=new OnBtnClickListener();
        btn1.setOnClickListener(onBtnClickListener);
        btn2.setOnClickListener(onBtnClickListener);
        btn3.setOnClickListener(onBtnClickListener);
        btn4.setOnClickListener(onBtnClickListener);

    }

    private class OnBtnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn1:
                    Intent intent1=new Intent(FirstActivity.this,TestActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.btn2:
                    Intent intent2=new Intent(FirstActivity.this,BetActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.btn3:
                    Intent intent3=new Intent(FirstActivity.this,BaijialActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.btn4:
                    Intent intent4=new Intent(FirstActivity.this,LuckPanActivity.class);
                    startActivity(intent4);
                    break;
            }
        }
    }
}
