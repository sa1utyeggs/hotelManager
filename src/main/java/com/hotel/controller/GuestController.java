package com.hotel.controller;

import com.hotel.enums.HttpResponseEnum;
import com.hotel.exception.HotelException;
import com.hotel.pojo.commom.ResponseResult;
import com.hotel.pojo.dto.GuestReserveDto;
import com.hotel.pojo.vo.GuestVo;
import com.hotel.service.impl.GuestServiceImpl;
import com.hotel.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 86183
 */
@Controller
@RequestMapping("/guest")
public class GuestController {
    @Autowired
    private GuestServiceImpl guestService;

    @GetMapping("/reservation")
    @ResponseBody
    public ResponseResult<List<GuestReserveDto>> getReservation(@RequestBody GuestVo vo) {
        try {
            if (!AssertUtil.isNull(vo)) {
                return ResponseResult.success("成功：获得预约信息", guestService.getReservation(vo));
            }
            return ResponseResult.fail("失败：传入值为 null");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail("失败：" + e.getMessage());
        }
    }

    @PostMapping("/reservation")
    @ResponseBody
    public ResponseResult<Object> insertReservation(@RequestBody GuestVo vo) {
        try {
            if (!AssertUtil.isNull(vo) && !AssertUtil.isNull(vo.getName()) && !AssertUtil.isNull(vo.getIdentity()) && !AssertUtil.isNull(vo.getPhone())) {
                Integer integer = guestService.insertReservation(vo);
                if (integer != 0) {
                    return ResponseResult.success("成功：预约");
                } else {
                    // 抛出异常
                    HotelException.throwException(HttpResponseEnum.DATABASE_FAIL);
                }
            }
            return ResponseResult.fail("失败：姓名、电话、身份证号为空");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail("失败：" + e.getMessage());
        }
    }
}
