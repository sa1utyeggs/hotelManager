package com.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.constant.MessageConst;
import com.hotel.mapper.UserMapper;
import com.hotel.pojo.dto.UserDto;
import com.hotel.pojo.entity.Message;
import com.hotel.pojo.entity.User;
import com.hotel.pojo.vo.UserLoginVo;
import com.hotel.service.UserService;
import com.hotel.utils.AssertUtil;
import com.hotel.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 86183
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Message login(UserLoginVo vo) {
        Message message = new Message().setCode(MessageConst.SUCCESS);
        if (AssertUtil.isNull(vo) || AssertUtil.isEmpty(vo.getName()) || AssertUtil.isEmpty(vo.getPassword())) {
            message.setCode(MessageConst.INPUT_ERROR).setMessage("vo为null或者用户密码为空");
        } else {
            User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, vo.getName()));
            if (AssertUtil.isNull(user)){
                message.setCode(MessageConst.INPUT_ERROR).setMessage("用户不存在");
            }else if (!vo.getPassword().equals(user.getPassword())){
                message.setCode(MessageConst.INPUT_ERROR).setMessage("密码不正确");
            }else {
                message.setMessage("登录成功").setObject(BeanCopyUtil.copyObject(user, UserDto.class));
            }
        }
        return message;
    }
}
