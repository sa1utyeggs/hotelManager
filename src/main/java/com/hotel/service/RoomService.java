package com.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.pojo.dto.RoomDto;
import com.hotel.pojo.dto.RoomGuestDto;
import com.hotel.pojo.entity.Room;
import com.hotel.pojo.vo.CheckinAndLeaveVo;
import com.hotel.pojo.vo.RoomConditionVo;
import com.hotel.pojo.vo.RoomVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86183
 */
@Service
public interface RoomService extends IService<Room> {
    /**
     * 条件获取 房间信息
     * @param vo vo
     * @return list
     */
    public List<RoomDto> getRoomDtos(RoomConditionVo vo);

    /**
     * 根据房间获得房间-客人信息
     * @param vo vo
     * @return list
     */
    public List<RoomGuestDto> getRoomGuestDto(RoomVo vo);

    /**
     * 离开
     * @param vo vo
     * @return int
     */
    public Integer checkin(CheckinAndLeaveVo vo);

    /**
     *  离开
     * @param vo vo
     * @return int
     */
    public Integer leave(CheckinAndLeaveVo vo);
}
