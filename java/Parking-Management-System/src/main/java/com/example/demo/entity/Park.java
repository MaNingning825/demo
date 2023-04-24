package com.example.demo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 停车表
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
public class Park implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车号
     */
    @TableId(value = "park_id", type = IdType.AUTO)
    private Integer parkId;

    /**
     * 车卡号（外键）
     */
    private Integer carId;

    /**
     * 车位号（外键）
     */
    private Integer stationId;

    /**
     * 停车卡类型 1普通卡 2VIP卡
     */
    private Integer cardType;

    /**
     * 车位类型 1小型 2中型 3大型
     */
    private Integer stationType;

    /**
     * 用户车牌号
     */
    private String carNumber;

    /**
     * 停车开始时间
     */
    private Date startPark;

    /**
     * 停车结束时间
     */
    private Date endPark;

    /**
     * 停车的收费
     */
    private BigDecimal fee;

    /**
     * 停车总时间
     */
    private Integer sumPark;

    /**
     * 状态，1未付款 2已付款
     */
    private Integer state;

    /**
     * 小叮当订单号
     */
    private String xddpayOrder;

    /**
     * 支付宝=43 微信支付=44
     */
    private Integer payType;

    /**
     * 表示用户实际支付的金额。一般会和money值一致，如果同时存在多个用户支付同一金额，就会和money存在一定差额，差额一般在1-2分钱上下，越多人同时付款，差额越大。
     */
    private Float realmoney;

    /**
     * 支付成功=success，其它均为失败
     */
    private String result;

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }
    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }
    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }
    public Integer getStationType() {
        return stationType;
    }

    public void setStationType(Integer stationType) {
        this.stationType = stationType;
    }
    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
    public Date getStartPark() {
        return startPark;
    }

    public void setStartPark(Date startPark) {
        this.startPark = startPark;
    }
    public Date getEndPark() {
        return endPark;
    }

    public void setEndPark(Date endPark) {
        this.endPark = endPark;
    }
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
    public Integer getSumPark() {
        return sumPark;
    }

    public void setSumPark(Integer sumPark) {
        this.sumPark = sumPark;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public String getXddpayOrder() {
        return xddpayOrder;
    }

    public void setXddpayOrder(String xddpayOrder) {
        this.xddpayOrder = xddpayOrder;
    }
    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
    public Float getRealmoney() {
        return realmoney;
    }

    public void setRealmoney(Float realmoney) {
        this.realmoney = realmoney;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Park{" +
            "parkId=" + parkId +
            ", carId=" + carId +
            ", stationId=" + stationId +
            ", cardType=" + cardType +
            ", stationType=" + stationType +
            ", carNumber=" + carNumber +
            ", startPark=" + startPark +
            ", endPark=" + endPark +
            ", fee=" + fee +
            ", sumPark=" + sumPark +
            ", state=" + state +
            ", xddpayOrder=" + xddpayOrder +
            ", payType=" + payType +
            ", realmoney=" + realmoney +
            ", result=" + result +
        "}";
    }
}
