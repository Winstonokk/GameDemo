package com.barnettwong.testgame.bean;

import java.io.Serializable;

/**
 * Created by wang on 2019/1/8 09:57
 */
public class NiuResBean implements Serializable {
    private int niuType;//1牛牛，2牛几，3没牛
    private int niuNum;//1牛牛情况下为10，2牛几情况下为几，3没牛情况下为0
    private int cardType;//最大牌花色
    private int cardNum;//最大牌牌值

    public NiuResBean() {
    }

    public NiuResBean(int niuType, int niuNum, int cardType, int cardNum) {
        this.niuType = niuType;
        this.niuNum = niuNum;
        this.cardType = cardType;
        this.cardNum = cardNum;
    }

    public int getNiuType() {
        return niuType;
    }

    public void setNiuType(int niuType) {
        this.niuType = niuType;
    }

    public int getNiuNum() {
        return niuNum;
    }

    public void setNiuNum(int niuNum) {
        this.niuNum = niuNum;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }
}
