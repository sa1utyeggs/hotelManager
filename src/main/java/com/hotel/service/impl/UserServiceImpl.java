package com.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.mapper.UserMapper;
import com.hotel.pojo.commom.ResponseResult;
import com.hotel.pojo.dto.UserDto;
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
    public ResponseResult<UserDto> login(UserLoginVo vo) {
        ResponseResult<UserDto> r = ResponseResult.success();
        if (AssertUtil.isNull(vo) || AssertUtil.isEmpty(vo.getName()) || AssertUtil.isEmpty(vo.getPassword())) {
            r = ResponseResult.fail("vo为null或者用户密码为空");
        } else {
            User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, vo.getName()));
            if (AssertUtil.isNull(user)){
                r = ResponseResult.fail("用户不存在");
            }else if (!vo.getPassword().equals(user.getPassword())){
                r = ResponseResult.fail("密码不正确");
            }else {
                r.setData(BeanCopyUtil.copyObject(user, UserDto.class));
            }
        }
        return r;
    }
}
