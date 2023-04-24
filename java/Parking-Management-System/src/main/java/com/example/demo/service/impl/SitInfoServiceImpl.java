package com.example.demo.service.impl;

import com.example.demo.entity.SitInfo;
import com.example.demo.mapper.SitInfoMapper;
import com.example.demo.service.SitInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车位信息表 服务实现类
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Service
public class SitInfoServiceImpl extends ServiceImpl<SitInfoMapper, SitInfo> implements SitInfoService {

    @Override
    public int countOfSit() {
        return this.baseMapper.countOfSit();
    }

    @Override
    public int countOfAvailableSit() {
        return this.baseMapper.countOfAvailableSit();
    }
}
