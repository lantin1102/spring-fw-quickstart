package com.lantin.spring.common.utils;

import java.util.UUID;

/**
 * Created on 2021/07/15/17:03 周四
 *
 * @author Lantin
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getUUIDNoUnderscore() {
        return getUUID().replace("-", "");
    }

    public static void main(String[] args) {

        String uuid = getUUID();
        System.out.println(uuid);

        String uuidNoUnderscore = getUUIDNoUnderscore();
        System.out.println(uuidNoUnderscore);
    }
}
