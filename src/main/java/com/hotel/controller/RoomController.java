package com.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hotel.pojo.commom.ResponseResult;
import com.hotel.pojo.dto.RoomDto;
import com.hotel.pojo.dto.RoomGuestDto;
import com.hotel.pojo.entity.Guest;
import com.hotel.pojo.entity.GuestReserve;
import com.hotel.pojo.entity.Room;
import com.hotel.pojo.entity.RoomType;
import com.hotel.pojo.vo.CheckinAndLeaveVo;
import com.hotel.pojo.vo.GuestVo;
import com.hotel.pojo.vo.RoomConditionVo;
import com.hotel.pojo.vo.RoomVo;
import com.hotel.service.GuestService;
import com.hotel.service.RoomService;
import com.hotel.service.impl.GuestServiceImpl;
import com.hotel.service.impl.RoomServiceImpl;
import com.hotel.utils.AssertUtil;
import com.hotel.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86183
 */
@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private GuestServiceImpl guestService;


//    @GetMapping("/page")
//    public String roomPage() {
//        return "room";
//    }

    @GetMapping("/room")
    @ResponseBody
    public ResponseResult<List<RoomDto>> getRooms(@RequestBody RoomConditionVo vo) {
        try {
            List<RoomDto> roomDtos = roomService.getRoomDtos(vo);
            return ResponseResult.success("成功：条件查询房间信息", roomDtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail("失败：" + e.getMessage(), new ArrayList<RoomDto>());
        }
    }

    @PostMapping("/room")
    @ResponseBody
    public ResponseResult<Object> insertRoom(@RequestBody RoomVo vo) {
        try {
            if (AssertUtil.isNull(vo)) {
                return ResponseResult.fail("失败：对象为空");
            }
            Room room = BeanCopyUtil.copyObject(vo, Room.class);
            room.setAvailable(true);
            // 保证 关键值 不为空
            if (AssertUtil.isNull(room.getRoomId())
                    || AssertUtil.isNull(room.getType())
                    || AssertUtil.isNull(room.getPrice())
                    || AssertUtil.isNull(room.getLevel())) {
                return ResponseResult.fail("失败：roomId、type、price、level 为空");
            }
            // 保证 roomId 不一样
            if (AssertUtil.isNotNull(roomService
                    .getOne(new LambdaQueryWrapper<Room>()
                            .eq(Room::getRoomId, room.getRoomId())))) {
                return ResponseResult.fail("失败：已存在同样 roomId 的房间");
            }
            int insert = roomService.getBaseMapper().insert(room);
            if (insert == 0) {
                return ResponseResult.fail("失败：数据库插入");
            }
            return ResponseResult.success("成功：添加房间");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail("失败：" + e.getMessage());
        }
    }

    @PutMapping("/room")
    @ResponseBody
    public ResponseResult<Object> updateRoom(@RequestBody RoomVo vo) {
        try {
            Room room = roomService.getById(vo.getId());
            if (AssertUtil.isNull(room)) {
                return ResponseResult.fail("失败：房间不存在");
            } else {
                roomService.updateById(BeanCopyUtil.copyObject(vo, Room.class));
                return ResponseResult.success("成功：修改房间信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail("失败：" + e.getMessage());
        }
    }

    @DeleteMapping("/room")
    @ResponseBody
    public ResponseResult<Object> deleteRoom(@RequestBody RoomVo vo) {
        try {
            if (AssertUtil.isNull(vo)) {
                return ResponseResult.fail("失败：房间不存在");
            }
            boolean delete = false;
            if (AssertUtil.isNotNull(vo.getId())) {
                delete = roomService.removeById(vo.getId());
            } else if (AssertUtil.isNotNull(vo.getRoomId())) {
                delete = roomService.remove(new LambdaQueryWrapper<Room>().eq(Room::getRoomId, vo.getRoomId()));
            }
            if (delete) {
                return ResponseResult.success("成功：删除房间信息");
            } else {
                return ResponseResult.fail("失败：数据库删除");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail("失败：" + e.getMessage());
        }
    }

    @GetMapping("/room-guest")
    @ResponseBody
    public ResponseResult<List<RoomGuestDto>> getRoomGuestDto(@RequestBody RoomVo vo) {
        try {
            if (AssertUtil.isNull(vo) || AssertUtil.isNull(vo.getRoomId())) {
                return ResponseResult.fail("失败：输入错误");
            }
            List<RoomGuestDto> roomGuestDto = roomService.getRoomGuestDto(vo);
            return ResponseResult.success("成功：获得该房间的客人信息", roomGuestDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail("失败：" + e.getMessage());
        }
    }

    @PostMapping("/room-guest")
    @ResponseBody
    public ResponseResult<Object> checkin(@RequestBody CheckinAndLeaveVo vo) {
        try {
            // 保证 用户 是存在的
            // 不存在就根据信息添加；无法添加就报错
            GuestVo guestVo = new GuestVo(vo.getGuestId(), vo.getName(), vo.getIdentity(), vo.getPhone());
            Guest guest;
            if (AssertUtil.isNull(guest = guestService.insertGuestIfNotExist(guestVo))) {
                return ResponseResult.fail("失败：用户信息不存在且无法插入");
            }
            vo.setGuestId(guest.getId());
            // 保证 房间 是存在的，且 available 为 true
            Room room = roomService.getById(vo.getRoomId());
            if (AssertUtil.isNull(room)) {
                return ResponseResult.fail("失败：该房间不存在");
            } else if (!room.getAvailable()) {
                return ResponseResult.fail("失败：该房间已被占用");
            }
            // 删除这个人所有的预约信息
            guestService.deleteReservationByGuestId(new GuestVo().setId(guest.getId()));
            // checkin
            roomService.checkin(vo);
            return ResponseResult.success("成功：入住");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail("失败：" + e.getMessage());
        }
    }

    @PutMapping("/room-guest")
    @ResponseBody
    public ResponseResult<Object> leave(@RequestBody CheckinAndLeaveVo vo) {
        try {

            // 保证 房间 是存在的，且 available 为 false
            Room room = roomService.getById(vo.getRoomId());
            if (AssertUtil.isNull(room)) {
                return ResponseResult.fail("失败：该房间不存在");
            } else if (room.getAvailable()) {
                return ResponseResult.fail("异常：该房间未被占用");
            }
            // 保证记录是存在的，且 记录id 不为 null
            if (AssertUtil.isNull(vo.getId())) {
                return ResponseResult.fail("失败：记录 id 为空");
            } else if (AssertUtil.isNull(roomService.getById(vo.getRoomId()))) {
                return ResponseResult.fail("失败：不存在此 id 的记录");
            }


            // leave
            roomService.leave(vo);
            return ResponseResult.success("成功：离开");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail("失败：" + e.getMessage());
        }
    }


}
