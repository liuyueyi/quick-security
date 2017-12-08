package com.hust.hui.quicksecurity.util;

import java.util.Objects;

/**
 * Created by yihui on 2017/12/8.
 */
public class BaseCheckUtil {

    public static boolean inAry(String checkObj, String[] ary) {
        for(String o : ary) {
            if (Objects.equals(o, checkObj)) {
                return true;
            }
        }

        return false;
    }

}
