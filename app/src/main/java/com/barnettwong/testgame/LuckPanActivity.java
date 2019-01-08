package com.barnettwong.testgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.barnettwong.testgame.widght.LuckyMonkeyPanelView;
import com.barnettwong.testgame.widght.LuckyMonkeyPanelView32;

import java.util.Random;

public class LuckPanActivity extends AppCompatActivity {


    private LuckyMonkeyPanelView32 lucky_panel;
    private Button btn_action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luckpan);

        lucky_panel = (LuckyMonkeyPanelView32) findViewById(R.id.lucky_panel);
        btn_action = (Button) findViewById(R.id.btn_action);

        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lucky_panel.isGameRunning()) {
                    lucky_panel.startGame();
                    btn_action.setText("停止");
                } else {
                    btn_action.setText("开始");
                    int stayIndex = new Random().nextInt(8);
                    Log.e("LuckyMonkeyPanelView", "====stay===" + stayIndex);
                    lucky_panel.tryToStop(stayIndex);
                }
            }
        });
    }
}
