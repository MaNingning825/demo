package com.example.demo.service;

import com.example.demo.entity.SitInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 车位信息表 服务类
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
public interface SitInfoService extends IService<SitInfo> {
    int countOfSit();
    int countOfAvailableSit();
}
