package io.mobile.finalproject.dlc;

import java.util.Date;

public class DLC {
    private int gameNo;
    private String dlc_name;

    public DLC(int gameNo, String dlc_name) {
        this.gameNo = gameNo;
        this.dlc_name = dlc_name;
    }

    public int getGameNo() {
        return gameNo;
    }

    public void setGameNo(int gameNo) {
        this.gameNo = gameNo;
    }

    public String getDlc_name() {
        return dlc_name;
    }

    public void setdlc_name(String dlc_name) {
        this.dlc_name = dlc_name;
    }

    @Override
    public String toString() {
        return "Dlc{" +
                "gameNo=" + gameNo +
                ", DLC_name='" + dlc_name + '\'' +
                '}';
    }
}
