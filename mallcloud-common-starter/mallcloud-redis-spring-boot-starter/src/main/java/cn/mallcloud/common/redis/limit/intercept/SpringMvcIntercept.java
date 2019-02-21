package cn.mallcloud.common.redis.limit.intercept;

import cn.mallcloud.common.redis.limit.RedisLimit;
import cn.mallcloud.common.redis.limit.annotation.SpringControllerLimit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Redis请求限流 SpringMVC拦截器
 * 借助于SpringControllerLimit实现
 *
 * @author zscat
 * @date 2018/5/21 13:57
 */
@Slf4j
public class SpringMvcIntercept extends HandlerInterceptorAdapter {

    private RedisLimit redisLimit;

    public SpringMvcIntercept(RedisLimit redisLimit) {
        this.redisLimit = redisLimit;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (redisLimit == null) {
            throw new NullPointerException("redisLimit is null");
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            SpringControllerLimit annotation = method.getMethodAnnotation(SpringControllerLimit.class);
            if (annotation == null) {
                //skip
                return true;
            }
            boolean limit = redisLimit.limit();
            if (!limit) {
                log.warn(annotation.errorMsg());
                response.sendError(annotation.errorCode(), annotation.errorMsg());
                return false;
            }
        }

        return true;

    }
}
