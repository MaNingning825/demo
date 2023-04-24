package com.example.demo.mapper;

import com.example.demo.entity.SitInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 车位信息表 Mapper 接口
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Mapper
public interface SitInfoMapper extends BaseMapper<SitInfo> {
    public int countOfSit();
    public int countOfAvailableSit();
}
