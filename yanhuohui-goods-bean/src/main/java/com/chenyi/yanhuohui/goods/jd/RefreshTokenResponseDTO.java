package com.chenyi.yanhuohui.goods.jd;

import lombok.Data;

@Data
public class RefreshTokenResponseDTO {
    private String uid;

    private String access_token;

    private String refresh_token;

    private Long time;

    /**
     * access_token过期时间
     */
    private Integer expires_in;

    /**
     * refresh_token过期时间
     */
    private Long refresh_token_expires;
}
