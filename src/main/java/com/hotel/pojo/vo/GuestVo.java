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
public class GuestVo {
    private Long id;

    private String name;
    private String identity;
    private String phone;
}
