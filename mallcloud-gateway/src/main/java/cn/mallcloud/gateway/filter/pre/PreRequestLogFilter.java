package cn.mallcloud.gateway.filter.pre;

import cn.mallcloud.common.constants.LogType;
import cn.mallcloud.common.constants.SecurityConstants;
import cn.mallcloud.common.entity.SysLog;
import cn.mallcloud.gateway.feign.SysLogService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;

import static cn.mallcloud.gateway.filter.MyFilterConstants.PRE_REQUEST_LOG_ORDER;
import static cn.mallcloud.gateway.filter.MyFilterConstants.PRE_TYPE;

/**
 * 请求日志记录
 *
 * @author zscat
 * @date 2017/12/17
 */
@Component
public class PreRequestLogFilter extends ZuulFilter {

    private static final Log log = LogFactory.getLog(PreRequestLogFilter.class);

    @Autowired
    private SysLogService logService;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_REQUEST_LOG_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest request = ctx.getRequest();
        log.info(String.format("send %s request to %s", request.getMethod(), request.getRequestURL().toString()));
        addLog(request, ctx);
        return null;
    }

    /**
     * 添加系统日志
     *
     * @param request 请求对象
     * @param ctx     RequestContext
     */
    private void addLog(HttpServletRequest request, RequestContext ctx) {
        final SysLog log = new SysLog();
        log.setCreateTime(new Date());
        log.setRemoteAddr(request.getRemoteAddr());
        log.setRequestUri(request.getRequestURI());
        log.setMethod(request.getMethod());

        if (StrUtil.containsAnyIgnoreCase(request.getRequestURI(),
                SecurityConstants.OAUTH_TOKEN_URL)) {
            // 记录登录日志
            log.setType(LogType.Login.name());
            log.setTitle(LogType.Login.name());
            log.setParams(queryParam(request));
            log.setCreateBy(request.getParameter("username"));
            logService.add(log);
        } else {
            if (!HttpMethod.GET.matches(request.getMethod())) {
                // 记录操作日志
                log.setType(LogType.Operation.name());
                log.setTitle(LogType.Operation.name());
                log.setCreateBy(ctx.getZuulRequestHeaders().get(SecurityConstants.USER_HEADER));
                logService.add(log);
            }
        }
    }

    /**
     * 获取get 参数
     */
    private String queryParam(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        final Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            params.append(name);
            params.append("=");
            if ("password".equals(name)) {
                params.append("******");
            } else {
                params.append(request.getParameter(name));
            }
            params.append("&");
        }
        if (params.length() > 0) {
            params.delete(params.length()-1, params.length());
            return params.toString();
        }
        return null;
    }
}
