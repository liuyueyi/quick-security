package com.hust.hui.quicksecurity.aspect.param;

import com.hust.hui.quicksecurity.exception.NoPermissionError;
import com.hust.hui.quicksecurity.util.BaseCheckUtil;
import com.hust.hui.quicksecurity.util.UrlExactUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * Created by yihui on 2017/12/8.
 */
@Component
@Aspect
@Slf4j
public class ParamCheckAspect {


    @Around("@annotation(ParamCheck)")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        // 参数校验
        Object[] args = joinPoint.getArgs();
        for (Object obj: args) {
            if (!checkArgs(obj)) { // 无权限，直接返回
                throw new NoPermissionError("越权的资源访问!");
            }
        }


        return joinPoint.proceed();
    }


    private boolean checkArgs(Object arg)  {
        try {
            Field[] fields = arg.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (f.isAnnotationPresent(UrlCheck.class)) {
                    // 需要校验参数了
                    f.setAccessible(true);
                    String url = (String) f.get(arg);

                    // 获取一级域名
                    url = UrlExactUtil.getRootDomain(url);
                    UrlCheck check = f.getAnnotation(UrlCheck.class);
                    if (!BaseCheckUtil.inAry(url, check.value())) {
                        return false;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            return true;
        }

        return true;
    }

}
