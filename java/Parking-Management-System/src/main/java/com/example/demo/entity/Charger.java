package com.example.demo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 停车收费标准表
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
public class Charger implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 停车卡类型 1普通卡 2VIP卡
     */
    private Integer cardType;

    /**
     * 车位类型 1小型 2中型 3大型
     */
    private Integer stationType;

    /**
     * 价格
     */
    private BigDecimal charge;

    /**
     * 状态 1上架 2下架
     */
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Charger{" +
            "id=" + id +
            ", cardType=" + cardType +
            ", stationType=" + stationType +
            ", charge=" + charge +
            ", state=" + state +
        "}";
    }
}
