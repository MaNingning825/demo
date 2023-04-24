package com.example.demo.service.impl;

import com.example.demo.entity.IncomeLog;
import com.example.demo.mapper.IncomeLogMapper;
import com.example.demo.service.IncomeLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Service
public class IncomeLogServiceImpl extends ServiceImpl<IncomeLogMapper, IncomeLog> implements IncomeLogService {

    @Override
    public Long sumOfIncome() {
        return this.baseMapper.sumOfIncome();
    }
}
