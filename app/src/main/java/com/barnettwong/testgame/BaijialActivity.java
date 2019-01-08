package com.barnettwong.testgame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.barnettwong.testgame.bean.CardBean;
import com.barnettwong.testgame.bean.NiuResBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wang on 2019/1/7 12:05
 */
public class BaijialActivity extends AppCompatActivity {
    @BindView(R.id.iv_dizhu_card1)
    ImageView ivDizhuCard1;
    @BindView(R.id.iv_dizhu_card2)
    ImageView ivDizhuCard2;
    @BindView(R.id.iv_dizhu_card3)
    ImageView ivDizhuCard3;
    @BindView(R.id.iv_dizhu_card4)
    ImageView ivDizhuCard4;
    @BindView(R.id.iv_dizhu_card5)
    ImageView ivDizhuCard5;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.iv_farmer_card1)
    ImageView ivFarmerCard1;
    @BindView(R.id.iv_farmer_card2)
    ImageView ivFarmerCard2;
    @BindView(R.id.iv_farmer_card3)
    ImageView ivFarmerCard3;
    @BindView(R.id.iv_farmer_card4)
    ImageView ivFarmerCard4;
    @BindView(R.id.iv_farmer_card5)
    ImageView ivFarmerCard5;
    @BindView(R.id.tvDizhuRes)
    TextView tvDizhuRes;
    @BindView(R.id.tvFinalRes)
    TextView tvFinalRes;
    @BindView(R.id.tvFarmerRes)
    TextView tvFarmerRes;

    private Unbinder unbinder;

    private List<Integer> hasShowCardNums;//已经出现的随机数
    private List<Integer> zhuangCardNums;//庄家五张牌随机数
    private List<Integer> xianCardNums;///闲家五张牌随机数
    private final int CARDNUMBER = 5;

    private NiuResBean zhuangResBean;//庄家牛的情况结果
    private NiuResBean xianResBean;//闲家牛的情况结果

    /*1.52张牌(1-13对应黑桃A,2,3,4,5...K，14-26对应红心，27-39对应梅花，40-52对应方块)
    2.牛牛(5张中任意三张为10的倍数，且另外两张也为10的倍数)>有牛（5张牌中的任意3张加起来为10的倍数，但另外2张不为10的倍数）>无牛（5张牌中的任意3张加起来不能成为10的倍数）
    3.剩余点数相同时，比较花色。黑红梅方。依次排列
    4.若都无牛，比较最大点数，点数相同比花色*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baijial);
        unbinder = ButterKnife.bind(this);

        hasShowCardNums = new ArrayList<>();
        zhuangCardNums = new ArrayList<>();
        xianCardNums = new ArrayList<>();
        //实例化
        zhuangResBean = new NiuResBean();
        xianResBean = new NiuResBean();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除历史牌，从头开始
                tvDizhuRes.setText("");
                tvFarmerRes.setText("");
                tvFinalRes.setText("");
                hasShowCardNums.clear();
                zhuangCardNums.clear();
                xianCardNums.clear();

                //开始给庄家，闲家发5张牌
                int iRand1 = (int) (Math.random() * 52 + 1);//庄家第一张牌
                setCardNum(1, iRand1);
                zhuangCardNums.add(iRand1);
                int iRand2 = (int) (Math.random() * 52 + 1);//庄家第二张牌
                //判断是否已经发过的牌
                iRand2 = checkCard(iRand2);
                Log.e("wang", "iRand2检测后返回的随机数：" + iRand2);
                setCardNum(2, iRand2);
                zhuangCardNums.add(iRand2);
                int iRand3 = (int) (Math.random() * 52 + 1);//庄家第三张牌
                //判断是否已经发过的牌
                iRand3 = checkCard(iRand3);
                Log.e("wang", "iRand3检测后返回的随机数：" + iRand3);
                setCardNum(3, iRand3);
                zhuangCardNums.add(iRand3);
                int iRand4 = (int) (Math.random() * 52 + 1);//庄家第四张牌
                //判断是否已经发过的牌
                iRand4 = checkCard(iRand4);
                Log.e("wang", "iRand4检测后返回的随机数：" + iRand4);
                setCardNum(4, iRand4);
                zhuangCardNums.add(iRand4);
                int iRand5 = (int) (Math.random() * 52 + 1);//庄家第五张牌
                //判断是否已经发过的牌
                iRand5 = checkCard(iRand5);
                Log.e("wang", "iRand5检测后返回的随机数：" + iRand5);
                setCardNum(5, iRand5);
                zhuangCardNums.add(iRand5);
                int iRand6 = (int) (Math.random() * 52 + 1);//闲家第一张牌
                //判断是否已经发过的牌
                iRand6 = checkCard(iRand6);
                Log.e("wang", "iRand6检测后返回的随机数：" + iRand6);
                setCardNum(6, iRand6);
                xianCardNums.add(iRand6);
                int iRand7 = (int) (Math.random() * 52 + 1);//闲家第二张牌
                //判断是否已经发过的牌
                iRand7 = checkCard(iRand7);
                Log.e("wang", "iRand7检测后返回的随机数：" + iRand7);
                setCardNum(7, iRand7);
                xianCardNums.add(iRand7);
                int iRand8 = (int) (Math.random() * 52 + 1);//闲家第三张牌
                //判断是否已经发过的牌
                iRand8 = checkCard(iRand8);
                Log.e("wang", "iRand8检测后返回的随机数：" + iRand8);
                setCardNum(8, iRand8);
                xianCardNums.add(iRand8);
                int iRand9 = (int) (Math.random() * 52 + 1);//闲家第四张牌
                //判断是否已经发过的牌
                iRand9 = checkCard(iRand9);
                Log.e("wang", "iRand9检测后返回的随机数：" + iRand9);
                setCardNum(9, iRand9);
                xianCardNums.add(iRand9);
                int iRand10 = (int) (Math.random() * 52 + 1);//闲家第五张牌
                //判断是否已经发过的牌
                iRand10 = checkCard(iRand10);
                Log.e("wang", "iRand10检测后返回的随机数：" + iRand10);
                setCardNum(10, iRand10);
                xianCardNums.add(iRand10);

                comparePlayer();
            }
        });
    }

    private void comparePlayer() {
        boolean zhuangHasNiu = checkNiu(zhuangCardNums, 1);
        boolean xianHasNiu = checkNiu(xianCardNums, 2);
        if (!zhuangHasNiu) {//庄没牛
            //得到最大的值，並記下花色
            CardBean cardBean1 = getLargeNum(zhuangCardNums, 1);
            zhuangResBean.setNiuType(3);
            zhuangResBean.setNiuNum(0);
            zhuangResBean.setCardType(cardBean1.getCardType());
            zhuangResBean.setCardNum(cardBean1.getNum());
            Log.e("wang", "庄家没有牛的花色和最大点数：" + cardBean1.getCardType() + "," + cardBean1.getNum());
        }
        if (!xianHasNiu) {//闲没牛
            //得到最大的值，並記下花色
            CardBean cardBean2 = getLargeNum(xianCardNums, 2);
            xianResBean.setNiuType(3);
            xianResBean.setNiuNum(0);
            xianResBean.setCardType(cardBean2.getCardType());
            xianResBean.setCardNum(cardBean2.getNum());
            Log.e("wang", "闲家没有牛的花色和最大点数：" + cardBean2.getCardType() + "," + cardBean2.getNum());
        }
        if(zhuangResBean.getNiuType()==1){
            tvDizhuRes.setText("庄家牛牛，最大牌为："+getLargeCard(zhuangResBean));
        }else if(zhuangResBean.getNiuType()==2){
            tvDizhuRes.setText("庄家牛"+zhuangResBean.getNiuNum()+"，最大牌为："+getLargeCard(zhuangResBean));
        }else{
            tvDizhuRes.setText("庄家没牛"+"，最大牌为："+getLargeCard(zhuangResBean));
        }

        if(xianResBean.getNiuType()==1){
            tvFarmerRes.setText("闲家牛牛，最大牌为："+getLargeCard(xianResBean));
        }else if(xianResBean.getNiuType()==2){
            tvFarmerRes.setText("闲家牛"+xianResBean.getNiuNum()+"，最大牌为："+getLargeCard(xianResBean));
        }else{
            tvFarmerRes.setText("闲家没牛"+"，最大牌为："+getLargeCard(xianResBean));
        }

        //通过比较两个对象来决定谁赢
        if(zhuangResBean.getNiuType()==1){//庄牛牛
            if(xianResBean.getNiuType()==1){//闲牛牛
                if(xianResBean.getCardNum()>zhuangResBean.getCardNum()){
                    //闲赢了
                    tvFinalRes.setText("闲赢了...");
                }else if(xianResBean.getCardNum()==zhuangResBean.getCardNum()){//比花色
                    if(xianResBean.getCardType()<zhuangResBean.getCardType()){
                        //闲赢了
                        tvFinalRes.setText("闲赢了...");
                    }else{
                        //庄赢了
                        tvFinalRes.setText("庄赢了...");
                    }
                }else if(xianResBean.getCardNum()<zhuangResBean.getCardNum()){
                    //庄赢了
                    tvFinalRes.setText("庄赢了...");
                }
            }else{
                //庄赢了
                tvFinalRes.setText("庄赢了...");
            }
        }else if(zhuangResBean.getNiuType()==2){//庄有牛但不为牛牛
            if(xianResBean.getNiuType()==1){//闲牛牛
                //闲赢了
                tvFinalRes.setText("闲赢了...");
            }else{
                if(xianResBean.getNiuType()==2){//闲有牛但不为牛牛
                    if(xianResBean.getCardNum()>zhuangResBean.getCardNum()){
                        //闲赢了
                        tvFinalRes.setText("闲赢了...");
                    }else if(xianResBean.getCardNum()==zhuangResBean.getCardNum()){//比花色
                        if(xianResBean.getCardType()<zhuangResBean.getCardType()){
                            //闲赢了
                            tvFinalRes.setText("闲赢了...");
                        }else{
                            //庄赢了
                            tvFinalRes.setText("庄赢了...");
                        }
                    }else if(xianResBean.getCardNum()<zhuangResBean.getCardNum()){
                        //庄赢了
                        tvFinalRes.setText("庄赢了...");
                    }
                }else if(xianResBean.getNiuType()==3){//闲没牛
                    //庄赢了
                    tvFinalRes.setText("庄赢了...");
                }
            }
        }else if(zhuangResBean.getNiuType()==3){//庄没牛
            if(xianResBean.getNiuType()<3){//闲有牛
                //闲赢了
                tvFinalRes.setText("闲赢了...");
            }else{//闲没牛
                if(xianResBean.getCardNum()>zhuangResBean.getCardNum()){
                    //闲赢了
                    tvFinalRes.setText("闲赢了...");
                }else if(xianResBean.getCardNum()==zhuangResBean.getCardNum()){//比花色
                    if(xianResBean.getCardType()<zhuangResBean.getCardType()){
                        //闲赢了
                        tvFinalRes.setText("闲赢了...");
                    }else{
                        //庄赢了
                        tvFinalRes.setText("庄赢了...");
                    }
                }else if(xianResBean.getCardNum()<zhuangResBean.getCardNum()){
                    //庄赢了
                    tvFinalRes.setText("庄赢了...");
                }
            }
        }

    }

    /**
     * 檢測是否有牛(1.牛牛2.有牛3.沒牛)
     *
     * @param CardNums
     */
    private boolean checkNiu(List<Integer> CardNums, int type) {
        for (int i = 0; i < CARDNUMBER; i++) {
            for (int j = i + 1; j < CARDNUMBER; j++) {
                for (int k = j + 1; k < CARDNUMBER; k++) {
                    if ((getTrueNum(CardNums.get(i)) + getTrueNum(CardNums.get(j)) + getTrueNum(CardNums.get(k))) % 10 == 0)    //保存有牛
                    {
                        Log.e("wang", (type == 1 ? "庄家" : "闲家") + "有牛的三個數：" + CardNums.get(i) + "," + CardNums.get(j) + "," + CardNums.get(k));
                        //是否是牛牛
                        checkNiuNiu(CardNums, i, j, k, type);
                        getLargeNum(CardNums, type);
                        return true;//有牛
                    }
                }
            }
        }
        return false;
    }

    private String getLargeCard(NiuResBean niuResBean){
        String res="";
        switch (niuResBean.getCardType()){
            case 1:
                res+="黑桃";
                break;
            case 2:
                res+="红心";
                break;
            case 3:
                res+="梅花";
                break;
            case 4:
                res+="方块";
                break;
        }
        if(niuResBean.getCardNum()<=10){
            res+=niuResBean.getCardNum();
        }else{
            int leftNum=niuResBean.getCardNum()%10;
            switch (leftNum){
                case 1://J
                    res+="J";
                    break;
                case 2://Q
                    res+="Q";
                    break;
                case 3://K
                    res+="K";
                    break;
            }
        }
        return res;
    }

    private CardBean getLargeNum(List<Integer> cardNums, int type) {
        List<CardBean> cardBeanList = new ArrayList<>();
        //拿到五張牌真实数值
        for (Integer randomNum : cardNums) {
            if ((randomNum >= 1) && (randomNum <= 13)) {//黑桃A,2,3,4,5...K
                cardBeanList.add(new CardBean(1, randomNum));
            } else if ((randomNum >= 14) && (randomNum <= 26)) {//红心
                int heartNum = randomNum - 13;
                cardBeanList.add(new CardBean(2, heartNum));
            } else if ((randomNum >= 27) && (randomNum <= 39)) {//梅花
                int clubNum = randomNum - 26;
                cardBeanList.add(new CardBean(3, clubNum));
            } else {//方块
                int diamondNum = randomNum - 39;
                cardBeanList.add(new CardBean(4, diamondNum));
            }
        }
        //遍历拿到最大值
        CardBean tempBean = new CardBean(4, 0);
        for (CardBean bean : cardBeanList) {
            if (bean.getNum() > tempBean.getNum()) {
                tempBean.setNum(bean.getNum());
                tempBean.setCardType(bean.getCardType());
            } else if (bean.getNum() == tempBean.getNum()) {
                if (bean.getCardType() < tempBean.getCardType()) {
                    tempBean.setCardType(bean.getCardType());
                }
            }
        }
        Log.e("wang", (type == 1 ? "庄家" : "闲家") + "最大牌的花色和点数：" + tempBean.getCardType() + "," + tempBean.getNum());
        if (type == 1) {
            zhuangResBean.setCardType(tempBean.getCardType());
            zhuangResBean.setCardNum(tempBean.getNum());
        } else {
            xianResBean.setCardType(tempBean.getCardType());
            xianResBean.setCardNum(tempBean.getNum());
        }
        return tempBean;
    }

    /**
     * 是否是牛牛
     *
     * @param cardNums
     * @param i
     * @param j
     * @param k
     */
    private void checkNiuNiu(List<Integer> cardNums, int i, int j, int k, int type) {
        int leftNum = 0;
        for (int m = 0; m < cardNums.size(); m++) {
            if ((m != i) && (m != k) && (m != j)) {
                leftNum += getTrueNum(cardNums.get(m));
            }
        }
        if (leftNum % 10 == 0) {//牛牛
            Log.e("wang", (type == 1 ? "庄家" : "闲家") + "牛牛");
            //赋值
            if (type == 1) {
                zhuangResBean.setNiuType(1);
                zhuangResBean.setNiuNum(10);
            } else {
                xianResBean.setNiuType(1);
                xianResBean.setNiuNum(10);
            }
        } else {//牛幾
            Log.e("wang", (type == 1 ? "庄家" : "闲家") + "牛" + leftNum % 10);
            //赋值
            if (type == 1) {
                zhuangResBean.setNiuType(2);
                zhuangResBean.setNiuNum(leftNum % 10);
            } else {
                xianResBean.setNiuType(2);
                xianResBean.setNiuNum(leftNum % 10);
            }
        }
    }

    private int getTrueNum(int randomNum) {
        if ((randomNum >= 1) && (randomNum <= 13)) {//黑桃A,2,3,4,5...K
            if (randomNum > 10) {//J,Q,K
                return 10;
            } else {
                return randomNum;
            }
        } else if ((randomNum >= 14) && (randomNum <= 26)) {//红心
            int heartNum = randomNum - 13;
            if (heartNum > 10) {//J,Q,K
                return 10;
            } else {
                return heartNum;
            }
        } else if ((randomNum >= 27) && (randomNum <= 39)) {//梅花
            int clubNum = randomNum - 26;
            if (clubNum > 10) {//J,Q,K
                return 10;
            } else {
                return clubNum;
            }
        } else {//方块
            int diamondNum = randomNum - 39;
            if (diamondNum > 10) {//J,Q,K
                return 10;
            } else {
                return diamondNum;
            }
        }
    }

    private int checkCard(int randomNum) {
        if (hasShowCardNums.size() > 0) {
            if (hasSameNum(randomNum)) {
                Log.e("wang", "有重复随机数：" + randomNum);
                randomNum = (int) (Math.random() * 52 + 1);
                Log.e("wang", "新随机数：" + randomNum);
                checkCard(randomNum);//递归去除重复随机数
            } else {
                return randomNum;
            }
        }
        return randomNum;
    }

    private boolean hasSameNum(int randomNum) {
        boolean res = false;
        for (Integer num : hasShowCardNums) {
            if (randomNum == num) {
                res = true;
            }
        }
        return res;
    }

    private void setCardNum(int randomType, int randomNum) {
        if ((randomNum >= 1) && (randomNum <= 13)) {//黑桃
            int spadeNum = randomNum;
            String spadeName = "spade_" + spadeNum;
            setCard(randomType, spadeName);
        } else if ((randomNum >= 14) && (randomNum <= 26)) {//红心
            int heartNum = randomNum - 13;
            String heartName = "heart_" + heartNum;
            setCard(randomType, heartName);
        } else if ((randomNum >= 27) && (randomNum <= 39)) {//梅花
            int clubNum = randomNum - 26;
            String clubName = "club_" + clubNum;
            setCard(randomType, clubName);
        } else {//方块
            int diamondNum = randomNum - 39;
            String diamondName = "diamond_" + diamondNum;
            setCard(randomType, diamondName);
        }
//        Log.e("wang","类型："+randomType+",随机数："+randomNum+"");
        hasShowCardNums.add(randomNum);
    }

    private void setCard(int randomType, String imgName) {
        switch (randomType) {
            case 1://庄1
                ivDizhuCard1.setImageResource(getimages(imgName));
                break;
            case 2://庄2
                ivDizhuCard2.setImageResource(getimages(imgName));
                break;
            case 3://庄3
                ivDizhuCard3.setImageResource(getimages(imgName));
                break;
            case 4://庄4
                ivDizhuCard4.setImageResource(getimages(imgName));
                break;
            case 5://庄5
                ivDizhuCard5.setImageResource(getimages(imgName));
                break;
            case 6://闲1
                ivFarmerCard1.setImageResource(getimages(imgName));
                break;
            case 7://闲2
                ivFarmerCard2.setImageResource(getimages(imgName));
                break;
            case 8://闲3
                ivFarmerCard3.setImageResource(getimages(imgName));
                break;
            case 9://闲4
                ivFarmerCard4.setImageResource(getimages(imgName));
                break;
            case 10://闲5
                ivFarmerCard5.setImageResource(getimages(imgName));
                break;
        }
    }

    public static int getimages(String name) {
        Class drawable = R.drawable.class;
        Field field = null;
        try {
            field = drawable.getField(name);
            int images = field.getInt(field.getName());
            return images;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
