package com.hust.hui.quicksecurity.aspect.cors;

import java.lang.annotation.*;

/**
 * 跨域支持, 直接用  CrossOrigin
 *
 * Created by yihui on 2017/12/8.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CorsEx {

}
