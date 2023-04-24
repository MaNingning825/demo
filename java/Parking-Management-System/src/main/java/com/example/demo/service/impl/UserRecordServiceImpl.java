package com.example.demo.service.impl;

import com.example.demo.entity.UserRecord;
import com.example.demo.mapper.UserRecordMapper;
import com.example.demo.service.UserRecordService;
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
public class UserRecordServiceImpl extends ServiceImpl<UserRecordMapper, UserRecord> implements UserRecordService {

}
