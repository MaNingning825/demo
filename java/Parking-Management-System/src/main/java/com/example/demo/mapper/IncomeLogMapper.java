package com.example.demo.mapper;

import com.example.demo.entity.IncomeLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Mapper
public interface IncomeLogMapper extends BaseMapper<IncomeLog> {
    public long sumOfIncome();
}
