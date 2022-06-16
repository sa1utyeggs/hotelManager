package com.hotel.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotel.pojo.entity.Guest;
import com.hotel.pojo.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author 86183
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomGuestDto {
    private Long id;

    private Room room;
    private Guest guest;
    private String remark;
    private Integer status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date checkinTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date leaveTime;
    private int days;
}
