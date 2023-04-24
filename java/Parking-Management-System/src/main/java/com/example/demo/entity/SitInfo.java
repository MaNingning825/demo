package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 车位信息表
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
public class SitInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "station_id", type = IdType.AUTO)
    private Integer stationId;

    /**
     * 车位号
     */
    private String station;

    /**
     * 车位标识
     */
    private String identifier;

    /**
     * 车位类型 1小型 2中型 3大型
     */
    private Integer stationType;

    /**
     * 状态 1可用 2已使用 3不可用
     */
    private Integer state;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }
    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public Integer getStationType() {
        return stationType;
    }

    public void setStationType(Integer stationType) {
        this.stationType = stationType;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SitInfo{" +
            "stationId=" + stationId +
            ", station=" + station +
            ", identifier=" + identifier +
            ", stationType=" + stationType +
            ", state=" + state +
        "}";
    }
}
