package cn.mallcloud.common.ribbon;

import cn.mallcloud.common.constants.CommonConstant;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ribbon扩展 为所有的Spring Mvc请求添加拦截器
 * 用于在请求间传递label信息
 *
 * @author zscat
 */
@Slf4j
public class XlabelMvcHeaderInterceptor extends HandlerInterceptorAdapter {

    /**
     * 每个实例(服务)的路由信息存在注册中心的metadata中
     * HystrixRequestVariableDefault 用于在线程中传递 x-label标签
     * 因为Hystrix自己维护线程,ThreadLocal就会失效
     */
    public static final HystrixRequestVariableDefault<List<String>> LABEL = new HystrixRequestVariableDefault<>();


    public static void initHystrixRequestContext(String labels) {
        log.debug("Init x-label: " + labels);
        if (!HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.initializeContext();
        }

        if (!StringUtils.isEmpty(labels)) {
            XlabelMvcHeaderInterceptor.LABEL.set(Arrays.asList(labels.split(CommonConstant.HEADER_LABEL_SPLIT)));
        } else {
            XlabelMvcHeaderInterceptor.LABEL.set(Collections.emptyList());
        }
    }

    public static void shutdownHystrixRequestContext() {
        if (HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.getContextForCurrentThread().shutdown();
        }
    }

    /**
     * x-label 依次传递下去 通过HystrixRequestVariableDefault
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String labels = request.getHeader(CommonConstant.HEADER_LABEL);
        XlabelMvcHeaderInterceptor.initHystrixRequestContext(labels);
        log.debug("Pass x-label by spring mvc: " + labels);
        return true;
    }


    /**
     * 请求处理完之后,销毁HystrixRequestVariableDefault, 资源回收
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        XlabelMvcHeaderInterceptor.shutdownHystrixRequestContext();
    }
}
