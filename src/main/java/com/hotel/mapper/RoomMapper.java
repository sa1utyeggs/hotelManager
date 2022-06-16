package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.pojo.entity.Room;
import com.hotel.pojo.entity.RoomType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 86183
 */
@Mapper
public interface RoomMapper extends BaseMapper<Room> {

    /**
     * 获得所有的 房间 类型-名字 对应
     * @return list
     */
    public List<RoomType> getRoomType();
}
