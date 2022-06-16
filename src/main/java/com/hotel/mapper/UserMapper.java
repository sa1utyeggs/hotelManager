package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 86183
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
