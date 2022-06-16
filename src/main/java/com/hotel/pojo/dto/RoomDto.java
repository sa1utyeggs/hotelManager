package com.hotel.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 86183
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomDto implements Serializable {
    private Long id;
    private String roomId;
    private Integer type;
    private String typeName;
    private Float price;
    private String remark;
    private Integer level;
    private Boolean available;
}
