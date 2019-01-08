package com.barnettwong.testgame.bean;

import java.io.Serializable;

/**
 * Created by wang on 2019/1/7 18:07
 */
public class CardBean implements Serializable {
    private int cardType;//花色
    private int num;//值，A-K

    public CardBean() {
    }

    public CardBean(int cardType, int num) {
        this.cardType = cardType;
        this.num = num;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
