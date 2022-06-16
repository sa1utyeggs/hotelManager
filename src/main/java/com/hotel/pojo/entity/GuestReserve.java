package com.hotel.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author 86183
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("guest_reserve")
public class GuestReserve {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long guestId;
    private Date reserveTime;
}
