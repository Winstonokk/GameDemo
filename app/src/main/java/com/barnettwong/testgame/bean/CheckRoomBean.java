package com.barnettwong.testgame.bean;

import java.util.List;

/**
 * Created by Zhanglibin on 2017/9/14.
 */

public class CheckRoomBean {


    /**
     * result : {"roomState":"0","roomUserList":[{"userId":10018,"userName":"神秘之剑。"}],"betUserCount":0,"memberCount":1,"gameTimes":0,"resultGain":"+0","roomOwnerLogo":"http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoJfYwNEuSyQuWzjFGyI40Zv9G2CIMicmHIJ2BXx0OichMiaToUzYAIoRQDDyb62ticKI5pzvt4iaDbbRQ/0","goldCount":600,"gameResult":""}
     * code : 0
     * message : 查询成功
     */

    private ResultBean result;
    private int code;
    private String message;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ResultBean {
        /**
         * roomState : 0
         * roomUserList : [{"userId":10018,"userName":"神秘之剑。"}]
         * betUserCount : 0
         * memberCount : 1
         * gameTimes : 0
         * resultGain : +0
         * roomOwnerLogo : http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoJfYwNEuSyQuWzjFGyI40Zv9G2CIMicmHIJ2BXx0OichMiaToUzYAIoRQDDyb62ticKI5pzvt4iaDbbRQ/0
         * goldCount : 600
         * gameResult :
         */

        private int roomState;
        private int betUserCount;
        private int memberCount;
        private int gameTimes;
        private String resultGain;
        private String roomOwnerLogo;
        private int goldCount;
        private String gameResult;
        private int gameState;
        private List<RoomUserListBean> roomUserList;

        public int getRoomState() {
            return roomState;
        }

        public void setRoomState(int roomState) {
            this.roomState = roomState;
        }

        public int getBetUserCount() {
            return betUserCount;
        }

        public void setBetUserCount(int betUserCount) {
            this.betUserCount = betUserCount;
        }

        public int getMemberCount() {
            return memberCount;
        }

        public void setMemberCount(int memberCount) {
            this.memberCount = memberCount;
        }

        public int getGameTimes() {
            return gameTimes;
        }

        public void setGameTimes(int gameTimes) {
            this.gameTimes = gameTimes;
        }

        public String getResultGain() {
            return resultGain;
        }

        public void setResultGain(String resultGain) {
            this.resultGain = resultGain;
        }

        public String getRoomOwnerLogo() {
            return roomOwnerLogo;
        }

        public void setRoomOwnerLogo(String roomOwnerLogo) {
            this.roomOwnerLogo = roomOwnerLogo;
        }

        public int getGoldCount() {
            return goldCount;
        }

        public void setGoldCount(int goldCount) {
            this.goldCount = goldCount;
        }

        public String getGameResult() {
            return gameResult;
        }

        public void setGameResult(String gameResult) {
            this.gameResult = gameResult;
        }

        public int getGameState() {
            return gameState;
        }

        public void setGameState(int gameState) {
            this.gameState = gameState;
        }

        public List<RoomUserListBean> getRoomUserList() {
            return roomUserList;
        }

        public void setRoomUserList(List<RoomUserListBean> roomUserList) {
            this.roomUserList = roomUserList;
        }

        public static class RoomUserListBean {
            /**
             * userId : 10018
             * userName : 神秘之剑。
             */

            private int userId;
            private String userName;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
