package com.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.pojo.dto.GuestReserveDto;
import com.hotel.pojo.entity.Guest;
import com.hotel.pojo.entity.GuestReserve;
import com.hotel.pojo.entity.Room;
import com.hotel.pojo.vo.GuestVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86183
 */
@Service
public interface GuestService extends IService<Guest> {
    /**
     * 获得客人的预约记录
     * @param vo
     * @return list
     */
    public List<GuestReserveDto> getReservation(GuestVo vo);

    /**
     * 预约
     * @param vo vo
     * @return 插入数量
     */
    public Integer insertReservation(GuestVo vo);
}
