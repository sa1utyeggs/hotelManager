package com.hotel.pojo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * @author 86183
 */
@Data
@Accessors(chain = true)
public class Message {
    private int code;
    private String message;
    private Object object;
}
