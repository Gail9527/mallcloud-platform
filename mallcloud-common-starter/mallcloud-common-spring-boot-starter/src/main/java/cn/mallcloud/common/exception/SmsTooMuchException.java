package cn.mallcloud.common.exception;


/**
 * 短信发送太频繁
 *
 * @author zscat
 */
public class SmsTooMuchException extends BusinessException {

    public SmsTooMuchException(String message) {
        super(message);
    }

}
