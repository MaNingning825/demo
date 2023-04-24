package com.example.demo.mapper;

import com.example.demo.entity.Park;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 停车表 Mapper 接口
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Mapper
public interface ParkMapper extends BaseMapper<Park> {

}
