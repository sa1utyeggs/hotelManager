package com.hotel.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author 86183
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("room_guest")
@Accessors(chain = true)
public class RoomGuest {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long roomId;
    private Long guestId;
    private String remark;
    private Integer status;
    private Date checkinTime;
    private Date leaveTime;
    private int days;
}
