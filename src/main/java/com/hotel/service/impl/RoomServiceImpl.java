package com.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.constant.HotelConst;
import com.hotel.mapper.GuestMapper;
import com.hotel.mapper.RoomGuestMapper;
import com.hotel.mapper.RoomMapper;
import com.hotel.pojo.dto.RoomDto;
import com.hotel.pojo.dto.RoomGuestDto;
import com.hotel.pojo.entity.Guest;
import com.hotel.pojo.entity.Room;
import com.hotel.pojo.entity.RoomGuest;
import com.hotel.pojo.entity.RoomType;
import com.hotel.pojo.vo.CheckinAndLeaveVo;
import com.hotel.pojo.vo.RoomConditionVo;
import com.hotel.pojo.vo.RoomVo;
import com.hotel.service.RoomService;
import com.hotel.utils.AssertUtil;
import com.hotel.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 86183
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomGuestMapper roomGuestMapper;

    @Autowired
    private GuestMapper guestMapper;

    @Autowired
    private GuestServiceImpl guestService;


    @Override
    public List<RoomDto> getRoomDtos(RoomConditionVo vo) {
        // 获取 房间类型对应map
        Map<Integer, RoomType> roomTypeMap = BeanCopyUtil.listToMapWithPrimaryKey(roomMapper.getRoomType(), RoomType::getType);
        // 条件查询
        List<Room> rooms = roomMapper.selectList(new LambdaQueryWrapper<Room>()
                .ne(AssertUtil.isNotNull(vo.getMinPrice()), Room::getPrice, vo.getMinPrice())
                .le(AssertUtil.isNotNull(vo.getMaxPrice()), Room::getPrice, vo.getMaxPrice())
                .eq(AssertUtil.isNotNull(vo.getAvailable()), Room::getAvailable, vo.getAvailable())
                .eq(AssertUtil.isNotNull(vo.getRoomId()), Room::getRoomId, vo.getRoomId())
                .eq(AssertUtil.isNotNull(vo.getType()), Room::getType, vo.getType())
                .eq(AssertUtil.isNotNull(vo.getLevel()), Room::getLevel, vo.getLevel()));
        List<RoomDto> roomDtos = BeanCopyUtil.copyList(rooms, RoomDto.class);
        // 填入 类型名称
        roomDtos.forEach(r -> {
            RoomType roomType;
            if (AssertUtil.isNotNull(roomType = roomTypeMap.get(r.getType()))) {
                r.setTypeName(roomType.getTypeName());
            } else {
                r.setTypeName("未知类型");
            }
        });
        return roomDtos;
    }

    @Override
    public List<RoomGuestDto> getRoomGuestDto(RoomVo vo) {
        // 获得记录
        List<RoomGuest> roomGuests = roomGuestMapper.selectList(new LambdaQueryWrapper<RoomGuest>()
                .eq(RoomGuest::getRoomId, vo.getRoomId())
                .orderByDesc(RoomGuest::getId));
        // 获得 房间信息
        Room room = roomMapper.selectById(vo.getRoomId());
        // 复制到 dto 中
        List<RoomGuestDto> roomGuestDtos = new ArrayList<>(roomGuests.size());
        roomGuests.forEach(rg -> {
            // 复制
            RoomGuestDto roomGuestDto = BeanCopyUtil.copyObject(rg, RoomGuestDto.class);
            // 获得用户
            Guest guest = guestMapper.selectById(rg.getGuestId());
            roomGuestDto.setRoom(room);
            roomGuestDto.setGuest(guest);
            // 添加
            roomGuestDtos.add(roomGuestDto);
        });
        return roomGuestDtos;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Integer checkin(CheckinAndLeaveVo vo) {
        // 前提条件：
        // 1、确保有 guest 信息
        // 2、确保有 房间 信息，且 available 为 true

        // 入住
        // 插入 入住信息
        int insert = roomGuestMapper.insert(new RoomGuest(null, vo.getRoomId(),
                vo.getGuestId(), vo.getRemark(),
                HotelConst.CHECKIN_STATUS,
                new Date(), null, vo.getDays()));
        // 修改 房间 available 状态 为 false
        int update = roomMapper.updateById(new Room().setId(vo.getRoomId()).setAvailable(false));

        // 任意一步插入失败
        if (insert == 0 || update == 0) {
            // 手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return insert + update;

    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Integer leave(CheckinAndLeaveVo vo) {
        // 前提条件：
        // 1、确保有 房间 信息
        // 2、确保有 入住信息

        // 离开
        // 修改：离开时间、status
        int update1 = roomGuestMapper.updateById(new RoomGuest().setId(vo.getId())
                .setLeaveTime(new Date())
                .setStatus(HotelConst.LEAVE_STATUS));
        // 修改 房间 available 状态
        int update2 = roomMapper.updateById(new Room().setId(vo.getRoomId()).setAvailable(true));
        if (update1 == 0 || update2 == 0) {
            // 手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return update1 + update2;
    }
}
