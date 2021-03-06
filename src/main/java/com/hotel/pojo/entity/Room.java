package com.hotel.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author 86183
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("room")
@Accessors(chain = true)
public class Room {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String roomId;
    private Integer type;
    private Float price;
    private String remark;
    private Integer level;
    private Boolean available;
}
