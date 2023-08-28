package com.chenyi.yanhuohui.goods.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

@Slf4j
public class MD5Utils {
    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        // Spring的工具类
        //org.springframework.util.DigestUtils.md5DigestAsHex(str.getBytes());
        // commons-codec包的工具类
        return DigestUtils.md5Hex(inStr);
    }

}
