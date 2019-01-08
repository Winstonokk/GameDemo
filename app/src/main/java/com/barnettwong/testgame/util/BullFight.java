package com.barnettwong.testgame.util;

/**
 * Created by wang on 2019/1/7 15:01
 */
import java.util.Scanner;

/*
---------游戏规则---------
1.允许2-6个人同时发牌
2.胜利者唯一，无庄家
3.无特殊的牌型，不计算特殊情况
    1.计算每个玩家的手牌的得分来判定谁是胜利者
    2.牛牛>有牛>无牛
    3.剩余点数相同时，比较花色。黑红梅方。依次排列
    4.若都无牛，比较最大点数，点数相同比花色
    5.计算手牌的分计算方式：
        5.1 牛牛获得100000分，有牛获得10000，无牛0分
        5.2 点数得分为牛1:1000分，牛2:2000分。。。依次类推
        5.3 有牛点数相同或者无牛或者牛牛，比较最大牌的点数和花色， 点数得分：点数*10+花色：黑（3），红（2）。。。依次类推
* */

public class BullFight           //斗牛
{
    private final String []FLOWERCOLOR = {"DIAMOND","SPADE","HEART","CLUB"};         //牌的四种花型
    private int [] playingCard;                                                      //扑克牌   52张  数字1-52
    private int playerNumber;                                                        //游戏人数   参加游戏的人数   2-6人
    private Player[] players;                                                        //玩家
    private Player winner;                                                           //获胜者
    private final int CARDNUMBER = 5;                                                //每个玩家的扑克牌数量

    public static void main(String[] args) {
        BullFight bF = new BullFight();     //开始游戏
        bF.dealCard();                      //发牌
        bF.comparePlayer();                 //比较
        bF.showAllPlayer();                 //显示所有牌
        bF.showWinner();                    //显示获胜者
    }

    public BullFight()
    {
        startGame();                              //开始游戏
        playingCard = new int[52];                //52张牌
        Scanner in = new Scanner(System.in);
        do
        {
            System.out.println("Please input the number of play games person:（请输入玩家个数：2-6）");
            playerNumber = in.nextInt();
        }while(playerNumber<2||playerNumber>6);
        players = new Player[playerNumber];
        for(int i = 0;i<52;i++)
        {
            playingCard[i] = i+1;
        }
    }

    public void startGame()                //开始游戏
    {
        System.out.println("-----------------------------------------------");
        System.out.println("--------  Welcome to the BullFight!!  ---------");
        System.out.println("---------        欢迎来到斗牛游戏       ----------");
        System.out.println("-----------------------------------------------");
    }

    public void comparePlayer()            //找出获胜者
    {
        int pos = 0;
        int maxScore = players[0].getSocre();
        for(int i = 0;i<playerNumber;i++)
        {
            if(maxScore<players[i].getSocre())
            {
                maxScore = players[i].getSocre();
                pos = i;
            }
        }
        winner = players[pos];
    }

    public void dealCard()                 //发牌   不洗牌，牌为顺序牌，发牌采用随机发牌
    {
        int temp[] = new int[CARDNUMBER];             //5张牌
        int pos = 0;
        for(int i = 0;i<playerNumber;i++)
        {
            for(int j = 0;j<CARDNUMBER;j++)
            {
                pos = (int)(Math.random()*playingCard.length);           //随机下标值
                while(playingCard[pos]==-1)             //防止随机数重复
                {
                    pos = (int)(Math.random()*playingCard.length);
                }
                temp[j] = playingCard[pos];
                playingCard[pos] = -1;                  //发过的牌不再出现
            }
            players[i] = new Player(temp);              //创建
            players[i].calCard();                       //发牌后就看有没有牛   初步加分
            players[i].addSocre();                      //这手牌总得分
        }
    }
    public void showWinner()               //显示获胜者
    {
        System.out.println();
        System.out.println("--------获胜玩家--------");
        winner.showFlowerColor();

    }

    public void showAllPlayer()            //显示本局所有玩家
    {
        System.out.println("所有玩家：  ");
        for(int i = 0;i<playerNumber;i++)
        {
            System.out.println();
            System.out.println("---------玩家"+(i+1)+"--------");
            players[i].showFlowerColor();
        }
    }

}

class Player                     //玩家
{
    private int[] playingCard;                                                        //5张扑克牌
    private final String []FLOWERCOLOR = {"DIAMOND","SPADE","HEART","CLUB"};          //扑克牌的四种花型
    private final int CARDNUMBER = 5;                                                 //每个玩家的扑克牌数量
    private CardData[] Cards;                                                         //五张手牌
    private CardData[] remainCards;                                                   //剩下的两张
    private short flag;                                                               //标记此玩家是否有牛  牛牛：1  有牛：2  无牛：3
    private int cardScore;                                                            //此牌得分


    public static void main(String[] args) {
        int[] temp = {7,21,30,8,14};
        Player player = new Player(temp);
        player.showFlowerColor();
        player.showRemain(1);
        System.out.println(player.showFlag());
    }            //单次检测

    public Player(){}
    public Player(int[] pCard)
    {
        playingCard = new int[CARDNUMBER];
        Cards = new CardData[CARDNUMBER];           //保存玩家手中的牌
        remainCards = new CardData[2];              //剩下的两张
        for(int i = 0;i<CARDNUMBER;i++)
        {
            playingCard[i] = pCard[i];

        }
        for(int i = 0;i<CARDNUMBER;i++)
        {
            Cards[i] = new CardData();              //填充每个对象
        }
        for(int i = 0;i<2;i++)
        {
            remainCards[i] = new CardData();
        }
        flag = 3;
    }

    public void translateFlowerColor()                      //将5张基础点数牌转化为花式点数不同的扑克牌
    {
        int point,fColor;            //点数和花色

        for(int i = 0;i<CARDNUMBER;i++)
        {

            point = playingCard[i]/4;
            fColor = playingCard[i]%4;
            if(fColor!=0)
            {
                point++;
            }
            if(point>10)
            {
                Cards[i].realPoint = 10;
            }
            else
            {
                Cards[i].realPoint = (point);
            }
            Cards[i].point = point;
            Cards[i].flowerColor = FLOWERCOLOR[fColor];
            Cards[i].playingCard = i;
        }
    }

    public void calCard()                                   //计算剩下的牌
    {
        translateFlowerColor();
        int tflag = 0;
        int temp[] = new int[3];
        for(int i = 0;i<CARDNUMBER;i++)
        {
            for(int j = i+1;j<CARDNUMBER;j++)
            {
                for(int k = j+1;k<CARDNUMBER;k++)
                {
                    if((Cards[i].realPoint+Cards[j].realPoint+Cards[k].realPoint)%10==0)    //保存有牛
                    {
                        //System.out.println(Cards[i].realPoint+"-"+Cards[j].realPoint+"-"+Cards[k].realPoint);
                        temp[0] = i;
                        temp[1] = j;
                        temp[2] = k;
                        flag = 2;
                        tflag++;
                        cardScore = 10000;                   //有牛10000分
                        if(cardScore!=100000)                //未找到牛牛就继续查找
                        {
                            recordRemain(temp);
                            if(cardScore==100000)
                            {
                                return;                    //牛牛找到就退出查找
                            }
                        }

                    }
                }
            }
        }
        if(tflag==0)
        {
            flag = 3;
            cardScore = 0;                      //无牛没分
        }
    }

    public void recordRemain(int[] t)                       //此处优化一下
    {
        int j = 0;
        int sum = (remainCards[0].realPoint+remainCards[1].realPoint)%10;
        int sumRe = 0;
        int pos[] = new int[2];
        for(int i = 0;i<CARDNUMBER;i++)            //找出剩下的牌是哪两张  并保存其数组下标
        {
            if(i!=t[1]&&i!=t[2]&&i!=t[0])
            {
                pos[j++] = i;
                // sumRe += Cards[i].realPoint;

            }
        }
        sumRe = (Cards[pos[0]].realPoint+Cards[pos[1]].realPoint)%10;
        //System.out.println(sumRe);
        if(sum<sumRe||sumRe==0)           //满足条件之后可能为牛牛
        {
            remainCards[0] = Cards[pos[0]];
            remainCards[1] = Cards[pos[1]];
            if(sumRe==0)
            {
                flag = 1;
                cardScore = 100000;                         //牛牛100000分
            }
        }
    }

    public short showFlag()
    {
        return flag;
    }                //显示该玩家手牌类型：牛牛，有牛，无牛

    public void showFlowerColor()                           //显示玩家手上的手牌
    {
        char JQK;
        //System.out.print("等级:"+flag+" --- 分数:"+cardScore+" --- ");
        if(flag==1)
        {
            System.out.println("------牛牛--");
        }
        else if(flag==2)
        {
            System.out.println("------牛"+(remainCards[0].realPoint+remainCards[1].realPoint)%10+"--");
        }
        else
        {
            System.out.println("------无牛--");
        }
        for(int i = 0;i<CARDNUMBER;i++)
        {
            if(Cards[i].point==1||(Cards[i].point>10&&Cards[i].point<15))
            {
                if(Cards[i].point==11)
                {
                    JQK = 'J';
                }
                else if(Cards[i].point==12)
                {
                    JQK = 'Q';
                }
                else if(Cards[i].point==13)
                {
                    JQK = 'K';
                }
                else
                {
                    JQK = 'A';
                }
                System.out.println(Cards[i].flowerColor+": "+JQK/*+"----"+Cards[i].realPoint+"-----"+playingCard[i]*/);
                continue;
            }
            System.out.println(Cards[i].flowerColor+": "+Cards[i].point/*+"----"+Cards[i].realPoint+"-----"+playingCard[i]*/);
        }
    }

    public void showRemain(int a)                           //显示斗牛后剩下两张牌的属性
    {
        calCard();
        if(flag==3)
        {
            System.out.println("无牛！！！");
            return;
        }
        System.out.println("--------------------------------");
        System.out.println(remainCards[0].flowerColor+": "+remainCards[0].point+"----"+remainCards[0].realPoint);
        System.out.println(remainCards[1].flowerColor+": "+remainCards[1].point+"----"+remainCards[1].realPoint);
    }

    public void getMax(int maxpos[])                        //获取五张牌里面得分最高的一张   返回最大值和其所在下标
    {
        maxpos[0] = 0;   //最大值
        maxpos[1] = 0;   //下标
        for(int i = 0;i<CARDNUMBER;i++)
        {
            if(maxpos[0]<Cards[i].point)
            {
                maxpos[0] = Cards[i].point;
                maxpos[1] = i;
            }
            else if(maxpos[0]==Cards[i].point)                       //点数相同时比较花色
            {
                if(playingCard[maxpos[1]]>playingCard[i])
                {
                    maxpos[0] = Cards[i].point;
                    maxpos[1] = i;
                }
            }
        }

    }

    public void addSocre()                                  //手牌花色点数加分   计算五张牌所得的最大得分
    {
        int maxpos[] = new int[2];                          //第一个保存最大值，第二个保存下标
        getMax(maxpos);
        if(cardScore==10000)
        {
            cardScore += ((remainCards[0].realPoint+remainCards[1].realPoint)%10)*1000;         //牛几的分
            cardScore += maxpos[0]*10+getSocreFlowerColor(Cards[maxpos[1]].flowerColor);        //最大的点数和花色加分
            return;
        }
        else
        {
            cardScore+=maxpos[0]*10+getSocreFlowerColor(Cards[maxpos[1]].flowerColor);         //最大的点数和花色加分
        }
    }

    public int getSocreFlowerColor(String temp)            //获取该花色所得得分
    {
        if(temp.equals("DIAMOND"))
        {
            return 0;
        }
        else if(temp.equals("SPADE"))
        {
            return 3;
        }
        else if(temp.equals("HEART"))
        {
            return 2;
        }
        else
        {
            return 1;
        }
    }

    public int getSocre()
    {
        return cardScore;
    }

}

class CardData                   //玩家手牌的数据   模拟一个公开的数据类似结构体
{
    public int point;                   //牌的点数     J = 10,Q = 11,K = 12
    public int realPoint;               //真正的点数    J=Q=K=10
    public String flowerColor;          //花色
    public int playingCard;             //保存基础点数

    public CardData()
    {
        playingCard = -1;
        point = -1;
        realPoint = -1;
        flowerColor = new String();
    }
}
