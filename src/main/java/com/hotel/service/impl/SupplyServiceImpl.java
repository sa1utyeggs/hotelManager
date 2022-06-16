package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.mapper.GuestMapper;
import com.hotel.mapper.SupplyMapper;
import com.hotel.pojo.entity.Guest;
import com.hotel.pojo.entity.Supply;
import com.hotel.service.GuestService;
import com.hotel.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 86183
 */
@Service
public class SupplyServiceImpl extends ServiceImpl<SupplyMapper, Supply> implements SupplyService {
    @Autowired
    private SupplyMapper supplyMapper;


}
