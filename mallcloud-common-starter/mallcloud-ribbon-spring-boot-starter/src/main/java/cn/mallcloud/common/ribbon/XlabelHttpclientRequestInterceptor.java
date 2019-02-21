package cn.mallcloud.common.ribbon;

import cn.mallcloud.common.constants.CommonConstant;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * httpclient request 拦截器 用于传递x-label标签
 *
 * @author zscat
 */
@Slf4j
public class XlabelHttpclientRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // 在请求之前初始化 Hystrix Context 避免报错:
        // HystrixRequestContext.initializeContext() must be called at the beginning of each request before RequestVariable functionality can be used.
        if (!HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.initializeContext();
        }

        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        String header = StringUtils.collectionToDelimitedString(XlabelMvcHeaderInterceptor.LABEL.get(), CommonConstant.HEADER_LABEL_SPLIT);
        log.debug("Pass x-label by httpclient: " + header);
        requestWrapper.getHeaders().add(CommonConstant.HEADER_LABEL, header);

        return execution.execute(requestWrapper, body);
    }
}
