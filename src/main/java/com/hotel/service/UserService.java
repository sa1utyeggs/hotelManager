package com.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.pojo.entity.Message;
import com.hotel.pojo.entity.User;
import com.hotel.pojo.vo.UserLoginVo;
import org.springframework.stereotype.Service;

/**
 * @author 86183
 */
@Service
public interface UserService extends IService<User> {
    /**
     * 用户登录
     * @return message
     * @param vo
     */
    public Message login(UserLoginVo vo);
}
