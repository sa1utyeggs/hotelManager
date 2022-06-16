package com.hotel.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotel.pojo.entity.Guest;
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
public class GuestReserveDto {
    private Long id;
    private Long guestId;
    private Guest guest;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date reserveTime;
}
