package com.barnettwong.testgame;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.barnettwong.testgame.bean.CheckRoomBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wang on 2019/1/5 14:35
 */
public class BetActivity extends AppCompatActivity {
    @BindView(R.id.imge_dice)
    ImageView mImgeDice;
    @BindView(R.id.image_start_game)
    ImageView mImageStartGame;
    @BindView(R.id.imge_share)
    ImageView mImgeShare;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.rl_result)
    LinearLayout rlResult;
    private AnimationDrawable mAnimation;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);
        //初始化黄油刀控件绑定框架
        mUnbinder = ButterKnife.bind(this);

        mImageStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(null);
            }
        });

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int iRand1 = (int) (Math.random() * 6 + 1);
            int iRand2 = (int) (Math.random() * 6 + 1);
            int iRand3 = (int) (Math.random() * 6 + 1);

            switch (iRand1) {
                case 1:
                    iv1.setImageResource(R.mipmap.dice1);
                    break;
                case 2:
                    iv1.setImageResource(R.mipmap.dice2);
                    break;
                case 3:
                    iv1.setImageResource(R.mipmap.dice3);
                    break;
                case 4:
                    iv1.setImageResource(R.mipmap.dice4);
                    break;
                case 5:
                    iv1.setImageResource(R.mipmap.dice5);
                    break;
                case 6:
                    iv1.setImageResource(R.mipmap.dice6);
                    break;

            }

            switch (iRand2) {
                case 1:
                    iv2.setImageResource(R.mipmap.dice1);
                    break;
                case 2:
                    iv2.setImageResource(R.mipmap.dice2);
                    break;
                case 3:
                    iv2.setImageResource(R.mipmap.dice3);
                    break;
                case 4:
                    iv2.setImageResource(R.mipmap.dice4);
                    break;
                case 5:
                    iv2.setImageResource(R.mipmap.dice5);
                    break;
                case 6:
                    iv2.setImageResource(R.mipmap.dice6);
                    break;

            }
            switch (iRand3) {
                case 1:
                    iv3.setImageResource(R.mipmap.dice1);
                    break;
                case 2:
                    iv3.setImageResource(R.mipmap.dice2);
                    break;
                case 3:
                    iv3.setImageResource(R.mipmap.dice3);
                    break;
                case 4:
                    iv3.setImageResource(R.mipmap.dice4);
                    break;
                case 5:
                    iv3.setImageResource(R.mipmap.dice5);
                    break;
                case 6:
                    iv3.setImageResource(R.mipmap.dice6);
                    break;

            }
            //显示三个骰子结果
            rlResult.setVisibility(View.VISIBLE);
        }
    };


    /**
     * 骰子动画
     */
    private void startAnimation(final CheckRoomBean countBean) {
        mImgeDice.setImageResource(R.drawable.anim_dice);
        mAnimation = (AnimationDrawable) mImgeDice.getDrawable();
        mImgeDice.setVisibility(View.VISIBLE);
        rlResult.setVisibility(View.GONE);
        mAnimation.start();

        mHandler.postDelayed(new Runnable() {
            public void run() {
                mAnimation.stop();
                mImgeDice.setVisibility(View.GONE);

                mHandler.sendMessage(mHandler.obtainMessage());
            }
        }, 3000);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
