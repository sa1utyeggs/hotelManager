package com.hotel.pojo.vo;

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
public class CheckinAndLeaveVo {
    private Long id;
    private Long roomId;
    /**
     * 用户信息
     */
    private Long guestId;
    private String name;
    private String identity;
    private String phone;

    private String remark;
    private int days;
}
