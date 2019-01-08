package com.barnettwong.testgame.widght;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.barnettwong.testgame.R;

/**
 * Created by jeanboy on 2017/4/20.
 */

public class LuckyMonkeyPanelView32 extends FrameLayout {


//    private ImageView bg_1;
//    private ImageView bg_2;

    private PanelItemView itemView1, itemView2, itemView3,
            itemView4, itemView5,
            itemView6, itemView7, itemView8,
            itemView9, itemView10, itemView11,
            itemView12, itemView13,
            itemView14, itemView15, itemView16,
            itemView17, itemView18, itemView19,
            itemView20, itemView21,
            itemView22, itemView23, itemView24,
            itemView25, itemView26, itemView27,
            itemView28, itemView29,
            itemView30, itemView31, itemView32;

    private ItemView[] itemViewArr = new ItemView[32];
    private int currentIndex = 0;
    private int currentTotal = 0;
    private int stayIndex = 0;

    private boolean isMarqueeRunning = false;
    private boolean isGameRunning = false;
    private boolean isTryToStop = false;

    private static final int DEFAULT_SPEED = 150;
    private static final int MIN_SPEED = 50;
    private int currentSpeed = DEFAULT_SPEED;

    public LuckyMonkeyPanelView32(@NonNull Context context) {
        this(context, null);
    }

    public LuckyMonkeyPanelView32(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LuckyMonkeyPanelView32(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_lucky_mokey_panel32, this);
        setupView();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startMarquee();
    }

    @Override
    protected void onDetachedFromWindow() {
        stopMarquee();
        super.onDetachedFromWindow();
    }

    private void setupView() {
//        bg_1 = (ImageView) findViewById(R.id.bg_1);
//        bg_2 = (ImageView) findViewById(R.id.bg_2);
        itemView1 = (PanelItemView) findViewById(R.id.item1);
        itemView2 = (PanelItemView) findViewById(R.id.item2);
        itemView3 = (PanelItemView) findViewById(R.id.item3);
        itemView4 = (PanelItemView) findViewById(R.id.item4);
        itemView5 = (PanelItemView) findViewById(R.id.item5);
        itemView6 = (PanelItemView) findViewById(R.id.item6);
        itemView7 = (PanelItemView) findViewById(R.id.item7);
        itemView8 = (PanelItemView) findViewById(R.id.item8);
        itemView9 = (PanelItemView) findViewById(R.id.item9);
        itemView10 = (PanelItemView) findViewById(R.id.item10);
        itemView11 = (PanelItemView) findViewById(R.id.item11);
        itemView12 = (PanelItemView) findViewById(R.id.item12);
        itemView13 = (PanelItemView) findViewById(R.id.item13);
        itemView14 = (PanelItemView) findViewById(R.id.item14);
        itemView15 = (PanelItemView) findViewById(R.id.item15);
        itemView16 = (PanelItemView) findViewById(R.id.item16);
        itemView17 = (PanelItemView) findViewById(R.id.item17);
        itemView18 = (PanelItemView) findViewById(R.id.item18);
        itemView19 = (PanelItemView) findViewById(R.id.item19);
        itemView20 = (PanelItemView) findViewById(R.id.item20);
        itemView21 = (PanelItemView) findViewById(R.id.item21);
        itemView22 = (PanelItemView) findViewById(R.id.item22);
        itemView23 = (PanelItemView) findViewById(R.id.item23);
        itemView24 = (PanelItemView) findViewById(R.id.item24);
        itemView25 = (PanelItemView) findViewById(R.id.item25);
        itemView26 = (PanelItemView) findViewById(R.id.item26);
        itemView27 = (PanelItemView) findViewById(R.id.item27);
        itemView28 = (PanelItemView) findViewById(R.id.item28);
        itemView29 = (PanelItemView) findViewById(R.id.item29);
        itemView30 = (PanelItemView) findViewById(R.id.item30);
        itemView31 = (PanelItemView) findViewById(R.id.item31);
        itemView32 = (PanelItemView) findViewById(R.id.item32);

        itemViewArr[0] = itemView1;
        itemViewArr[1] = itemView2;
        itemViewArr[2] = itemView3;
        itemViewArr[3] = itemView4;
        itemViewArr[4] = itemView5;
        itemViewArr[5] = itemView6;
        itemViewArr[6] = itemView7;
        itemViewArr[7] = itemView8;
        itemViewArr[8] = itemView9;
        itemViewArr[9] = itemView10;
        itemViewArr[10] = itemView11;
        itemViewArr[11] = itemView12;
        itemViewArr[12] = itemView13;
        itemViewArr[13] = itemView14;
        itemViewArr[14] = itemView15;
        itemViewArr[15] = itemView16;
        itemViewArr[16] = itemView17;
        itemViewArr[17] = itemView18;
        itemViewArr[18] = itemView19;
        itemViewArr[19] = itemView20;
        itemViewArr[20] = itemView21;
        itemViewArr[21] = itemView22;
        itemViewArr[22] = itemView23;
        itemViewArr[23] = itemView24;
        itemViewArr[24] = itemView25;
        itemViewArr[25] = itemView26;
        itemViewArr[26] = itemView27;
        itemViewArr[27] = itemView28;
        itemViewArr[28] = itemView29;
        itemViewArr[29] = itemView30;
        itemViewArr[30] = itemView31;
        itemViewArr[31] = itemView32;
    }

    private void stopMarquee() {
        isMarqueeRunning = false;
        isGameRunning = false;
        isTryToStop = false;
    }

    private void startMarquee() {
        isMarqueeRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isMarqueeRunning) {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    post(new Runnable() {
                        @Override
                        public void run() {
//                            if (bg_1 != null && bg_2 != null) {
//                                if (VISIBLE == bg_1.getVisibility()) {
//                                    bg_1.setVisibility(GONE);
//                                    bg_2.setVisibility(VISIBLE);
//                                } else {
//                                    bg_1.setVisibility(VISIBLE);
//                                    bg_2.setVisibility(GONE);
//                                }
//                            }
                        }
                    });
                }
            }
        }).start();
    }

    private long getInterruptTime() {
        currentTotal++;
        if (isTryToStop) {
            currentSpeed += 10;
            if (currentSpeed > DEFAULT_SPEED) {
                currentSpeed = DEFAULT_SPEED;
            }
        } else {
            if (currentTotal / itemViewArr.length > 0) {
                currentSpeed -= 10;
            }
            if (currentSpeed < MIN_SPEED) {
                currentSpeed = MIN_SPEED;
            }
        }
        return currentSpeed;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void startGame() {
        isGameRunning = true;
        isTryToStop = false;
        currentSpeed = DEFAULT_SPEED;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isGameRunning) {
                    try {
                        Thread.sleep(getInterruptTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    post(new Runnable() {
                        @Override
                        public void run() {
                            int preIndex = currentIndex;
                            currentIndex++;
                            if (currentIndex >= itemViewArr.length) {
                                currentIndex = 0;
                            }

                            itemViewArr[preIndex].setFocus(false);
                            itemViewArr[currentIndex].setFocus(true);

                            if (isTryToStop && currentSpeed == DEFAULT_SPEED && stayIndex == currentIndex) {
                                isGameRunning = false;
                            }
                        }
                    });
                }
            }
        }).start();
    }

    public void tryToStop(int position) {
        stayIndex = position;
        isTryToStop = true;
    }

}
