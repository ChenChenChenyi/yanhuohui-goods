package com.chenyi.yanhuohui.goods.bean.dto;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class JDResponseDTO<T> {

    /**
     * 执行结果
     */
    boolean success;

    /**
     * 错误码
     */
    String resultCode;

    /**
     * 错误描述
     */
    String resultMessage;

    /**
     * 具体结果
     */
    T result;

}
