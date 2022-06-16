package com;

import com.hotel.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test {
    @Autowired
    private RoomMapper roomMapper;

    @org.junit.jupiter.api.Test
    public void test(){
        System.out.println(roomMapper.getRoomType());
    }
}
