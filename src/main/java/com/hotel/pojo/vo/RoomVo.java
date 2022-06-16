package com.hotel.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 86183
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomVo {
    private Long id;

    private String roomId;
    private Integer type;
    private Float price;
    private String remark;
    private Integer level;
    private Boolean available;
}
