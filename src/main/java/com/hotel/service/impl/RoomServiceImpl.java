package com.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.constant.MessageConst;
import com.hotel.mapper.RoomMapper;
import com.hotel.mapper.UserMapper;
import com.hotel.pojo.dto.UserDto;
import com.hotel.pojo.entity.Message;
import com.hotel.pojo.entity.Room;
import com.hotel.pojo.entity.User;
import com.hotel.pojo.vo.UserLoginVo;
import com.hotel.service.RoomService;
import com.hotel.service.UserService;
import com.hotel.utils.AssertUtil;
import com.hotel.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 86183
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Autowired
    private RoomMapper roomMapper;


}
