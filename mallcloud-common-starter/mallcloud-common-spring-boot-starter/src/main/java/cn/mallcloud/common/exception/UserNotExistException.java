package cn.mallcloud.common.exception;


/**
 * 用户未存在
 *
 * @author zscat
 */
public class UserNotExistException extends BusinessException {

    public UserNotExistException(String message) {
        super(message);
    }

}
