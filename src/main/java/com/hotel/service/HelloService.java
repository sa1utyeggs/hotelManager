package com.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.entity.Hello;
import org.springframework.stereotype.Service;

/**
 * @author 86183
 */
@Service
public interface HelloService extends IService<Hello> {


    public void test();
}
