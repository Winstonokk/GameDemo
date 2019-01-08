package com.barnettwong.testgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1=findViewById(R.id.iv1);
        iv2=findViewById(R.id.iv2);
        iv3=findViewById(R.id.iv3);

        //0-100,100-400,400-600暂时分成三段
        setTranslation(iv1,0,100,5000,1,1);
        setTranslation(iv2,0,100,9000,2,1);
        setTranslation(iv3,0,100,8000,3,1);
    }

    public void setTranslation(ImageView iv, float fromY, float toY, long duration, final int ivType, final int wayType){
        Animation translateAnimation = new TranslateAnimation(0,0,fromY,toY);//平移动画  从0,0,平移到0,100
        translateAnimation.setDuration(duration);//动画持续的时间为
        iv.setAnimation(translateAnimation);//给imageView添加的动画效果
        translateAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
        translateAnimation.setFillAfter(true);//不回到起始位置
        //如果不添加setFillEnabled和setFillAfter则动画执行结束后会自动回到远点
        translateAnimation.startNow();//动画开始执行 放在最后即可

        translateAnimation.setAnimationListener(
                new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {//开始时

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {//结束时
                        if(ivType==1){//第一个参赛者
                            if(wayType==1){//第一段路
                                //执行完毕开始第二段
                                setTranslation(iv1,100,400,13000,1,2);
                            }else if(wayType==2){//第二段路
                                //执行完毕开始第三段
                                setTranslation(iv1,400,600,9000,1,3);
                            }
                        }else if(ivType==2){//第二个参赛者
                            if(wayType==1){//第一段路
                                //执行完毕开始第二段
                                setTranslation(iv2,100,400,17000,2,2);
                            }else if(wayType==2){//第二段路
                                //执行完毕开始第三段
                                setTranslation(iv2,400,600,7500,2,3);
                            }
                        }else if(ivType==3){//第三个参赛者
                            if(wayType==1){//第一段路
                                //执行完毕开始第二段
                                setTranslation(iv3,100,400,20000,3,2);
                            }else if(wayType==2){//第二段路
                                //执行完毕开始第三段
                                setTranslation(iv3,400,600,10000,3,3);
                            }
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {//进行时

                    }
                });
    }


}
