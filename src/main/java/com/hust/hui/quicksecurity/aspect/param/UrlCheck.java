package com.hust.hui.quicksecurity.aspect.param;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Created by yihui on 2017/12/8.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UrlCheck {

    /**
     * url检测，域名白名单
     *
     * @return
     */
    @AliasFor("whiteList")
    String[] value() default {};


    @AliasFor("value")
    String[] whiteList() default {};

}
