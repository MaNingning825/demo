package com.example.demo.service;

import com.example.demo.entity.IncomeLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
public interface IncomeLogService extends IService<IncomeLog> {
    Long sumOfIncome();
}
