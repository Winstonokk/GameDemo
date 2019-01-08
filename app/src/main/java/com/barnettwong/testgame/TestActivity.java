package com.barnettwong.testgame;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wang on 2019/1/5 15:14
 */
public class TestActivity extends AppCompatActivity {
    private ImageView mImgRolling;
    private TextView mTxtResult;
    private Button mBtnRollDice;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //文本展示结果
            int iRand = (int) (Math.random()*6+1);
            mTxtResult.setText("点数：" + iRand);

            switch (iRand){
                case 1:
                    mImgRolling.setImageResource(R.mipmap.dice1);
                    break;
                case 2:
                    mImgRolling.setImageResource(R.mipmap.dice2);
                    break;
                case 3:
                    mImgRolling.setImageResource(R.mipmap.dice3);
                    break;
                case 4:
                    mImgRolling.setImageResource(R.mipmap.dice4);
                    break;
                case 5:
                    mImgRolling.setImageResource(R.mipmap.dice5);
                    break;
                case 6:
                    mImgRolling.setImageResource(R.mipmap.dice6);
                    break;

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mImgRolling = (ImageView) findViewById(R.id.imgRolling);
        mTxtResult = (TextView) findViewById(R.id.txtResult);
        mBtnRollDice = (Button) findViewById(R.id.btnRollDice);

        mBtnRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AnimationDrawable anim = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_rolling);
                mImgRolling.setImageDrawable(anim);
                anim.start();

                //启动background thread 进行倒计时5s
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        anim.stop();
//                        Message message = new Message();
//                        handler.sendMessage(message);
                        handler.sendMessage(handler.obtainMessage());

                    }
                }).start();

            }
        });
    }

}
