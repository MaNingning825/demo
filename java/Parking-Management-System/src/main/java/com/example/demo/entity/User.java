package com.example.demo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 车卡号
     */
    @TableId(value = "car_id", type = IdType.AUTO)
    private Integer carId;

    /**
     * 微信唯一标识
     */
    private String openid;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户密码
     */
    private String password;

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
     * 用户手机
     */
    private String tel;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别 0：未知、1：男、2：女
     */
    private Integer gender;

    /**
     * 用户余额
     */
    private BigDecimal overage;

    /**
     * 是否停车 0未停车，停车车位id
     */
    private Integer parkingLot;

    /**
     * 状态，1正常 2冻结 3欠费
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public BigDecimal getOverage() {
        return overage;
    }

    public void setOverage(BigDecimal overage) {
        this.overage = overage;
    }
    public Integer getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(Integer parkingLot) {
        this.parkingLot = parkingLot;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
            "carId=" + carId +
            ", openid=" + openid +
            ", nickName=" + nickName +
            ", password=" + password +
            ", cardType=" + cardType +
            ", stationType=" + stationType +
            ", carNumber=" + carNumber +
            ", tel=" + tel +
            ", avatarUrl=" + avatarUrl +
            ", gender=" + gender +
            ", overage=" + overage +
            ", parkingLot=" + parkingLot +
            ", state=" + state +
            ", createTime=" + createTime +
        "}";
    }
}
