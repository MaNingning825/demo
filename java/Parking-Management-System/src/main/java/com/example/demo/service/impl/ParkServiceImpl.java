package com.example.demo.service.impl;

import com.example.demo.entity.Park;
import com.example.demo.mapper.ParkMapper;
import com.example.demo.service.ParkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 停车表 服务实现类
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Service
public class ParkServiceImpl extends ServiceImpl<ParkMapper, Park> implements ParkService {

}
