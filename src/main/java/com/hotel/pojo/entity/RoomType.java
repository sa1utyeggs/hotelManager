package com.hotel.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("room_type")
public class RoomType {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String roomId;
    private Integer type;
    private String typeName;

}
