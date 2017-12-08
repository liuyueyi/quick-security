package com.hust.hui.quicksecurity.aspect.refer;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Created by yihui on 2017/12/8.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReferCheck {

    /**
     * 默认全部放过
     * @return
     */
    @AliasFor("whiteList")
    String[] value() default { "*" };


    @AliasFor("value")
    String[] whiteList() default {"*"};

}
