package com.hotel.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.entity.Hello;
import com.hotel.mapper.HelloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 86183
 */
@Service
public class HelloServiceImpl extends ServiceImpl<HelloMapper, Hello> implements HelloService {
    @Autowired
    private HelloMapper helloMapper;

    @Override
    public void test() {
        System.out.println(helloMapper.selectOne(new LambdaQueryWrapper<Hello>()));
    }
}
