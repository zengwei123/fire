package com.cnitpm.z_home.Model;

public class P_WX {

    /**
     * appid : wx3732146d5112e69c
     * mchid : 1300073401
     * prepayid : wx1016230227912044e1d9bfe81096002400
     * noncestr : 677E09724F0E2DF9B6C000B75B5DA10D
     * timeStamp : 1568103782
     * sign : 9FDBD2E9A4B75D94CA52E3DB21C3290F
     * TradeNo : Cfeks-wxazapp-20190910162301
     */

    private String appid;
    private String mchid;
    private String prepayid;
    private String noncestr;
    private String timeStamp;
    private String sign;
    private String TradeNo;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTradeNo() {
        return TradeNo;
    }

    public void setTradeNo(String TradeNo) {
        this.TradeNo = TradeNo;
    }
}
