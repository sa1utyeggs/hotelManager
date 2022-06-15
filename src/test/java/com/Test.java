package com;

import com.hotel.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test {
    @Autowired
    private HelloService helloService;
    @org.junit.jupiter.api.Test
    public void test(){
        helloService.test();
    }
}
