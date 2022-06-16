package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.mapper.GuestMapper;
import com.hotel.mapper.RoomMapper;
import com.hotel.pojo.entity.Guest;
import com.hotel.pojo.entity.Room;
import com.hotel.service.GuestService;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 86183
 */
@Service
public class GuestServiceImpl extends ServiceImpl<GuestMapper, Guest> implements GuestService {
    @Autowired
    private GuestMapper guestMapper;


}
