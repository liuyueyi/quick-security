package com.hust.hui.quicksecurity.aspect.refer;

import com.hust.hui.quicksecurity.util.BaseCheckUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yihui on 2017/12/8.
 */
@Component
@Aspect
public class ReferAspect {


    @Before(value = "@annotation(referCheck)")
    public void beforeProcess(JoinPoint joinPoint, ReferCheck referCheck) throws IOException {
        String[] whiteList = referCheck.value();
        if(whiteList.length == 1 && "*".equalsIgnoreCase(whiteList[0])) {
            return;
        }


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


        // 获取referer
        String referer = request.getHeader("referer");



        // 判断referer是否在白名单中
        // xxx 如果referer为Null， 且你的白名单也支持了null，则表示这类不会被拦住
        if (BaseCheckUtil.inAry(referer, whiteList)) {
            return;
        }


        // 若不在白名单，则默认返回403状态码
        HttpServletResponse response =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.sendError(403);
    }

}
