package com.lze_shop.lze_shop.common.classes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class response<T>  {
    private int code;
    private String msg;
    private T data;
}
