package com.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.mapper.GuestMapper;
import com.hotel.mapper.GuestReserveMapper;
import com.hotel.mapper.RoomMapper;
import com.hotel.pojo.commom.ResponseResult;
import com.hotel.pojo.dto.GuestReserveDto;
import com.hotel.pojo.entity.Guest;
import com.hotel.pojo.entity.GuestReserve;
import com.hotel.pojo.entity.Room;
import com.hotel.pojo.vo.GuestVo;
import com.hotel.service.GuestService;
import com.hotel.service.RoomService;
import com.hotel.utils.AssertUtil;
import com.hotel.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 86183
 */
@Service
public class GuestServiceImpl extends ServiceImpl<GuestMapper, Guest> implements GuestService {
    @Autowired
    private GuestMapper guestMapper;

    @Autowired
    private GuestReserveMapper guestReserveMapper;

    @Override
    public List<GuestReserveDto> getReservation(GuestVo vo) {
        // 通过 vo 获得 guest信息
        Guest guest = getGuest(vo);
        // 通过 guest 信息 获得预约信息
        if (AssertUtil.isNotNull(guest)) {
            List<GuestReserve> guestReserves = guestReserveMapper.selectList(new LambdaQueryWrapper<GuestReserve>()
                    .eq(GuestReserve::getGuestId, guest.getId()));
            List<GuestReserveDto> guestReserveDtos = BeanCopyUtil.copyList(guestReserves, GuestReserveDto.class);
            // 设置 用户信息
            guestReserveDtos.forEach(gr -> {
                gr.setGuest(guest);
            });
            return guestReserveDtos;
        }
        return new ArrayList<>(0);
    }

    @Override
    public Integer insertReservation(GuestVo vo) {
        // 先取得 guest 信息
        Guest guest = insertGuest(vo);
        // 再插入 预约 信息
        if (AssertUtil.isNotNull(guest)) {
            return guestReserveMapper.insert(new GuestReserve(null, guest.getId(), new Date()));
        }
        return 0;
    }

    public Guest insertGuest(GuestVo vo) {
        Guest guest;
        if (AssertUtil.isNull(guest = getGuest(vo))) {
            // 若 guest 已存在 ，就不需要再插入
            guest = BeanCopyUtil.copyObject(vo, Guest.class);
            guestMapper.insert(guest);
        }
        return guest;
    }

    public Guest getGuest(GuestVo vo) {
        return guestMapper.selectOne(new LambdaQueryWrapper<Guest>()
                .eq(AssertUtil.isNotNull(vo.getId()), Guest::getId, vo.getId())
                .eq(AssertUtil.isNotNull(vo.getName()), Guest::getName, vo.getName())
                .eq(AssertUtil.isNotNull(vo.getIdentity()), Guest::getIdentity, vo.getIdentity())
                .eq(AssertUtil.isNotNull(vo.getPhone()), Guest::getPhone, vo.getPhone()));
    }

    public Guest insertGuestIfNotExist(GuestVo guestVo) {
        Guest guest = getGuest(guestVo);
        if (AssertUtil.isNull(guest)) {
            // 如果 不存在此用户，尝试插入用户信息
            if (AssertUtil.isNull(guestVo.getName())
                    && AssertUtil.isNull(guestVo.getIdentity())
                    && AssertUtil.isNull(guestVo.getPhone())) {
                // 如果三个必要信息都有，插入
                return insertGuest(guestVo);
            } else {
                // 缺失必要信息，尝试失败
                return null;
            }
        }
        return guest;
    }
}
