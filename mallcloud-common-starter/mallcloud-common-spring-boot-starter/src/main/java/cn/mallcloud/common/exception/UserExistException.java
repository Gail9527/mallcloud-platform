package cn.mallcloud.common.exception;


/**
 * 用户已存在
 *
 * @author zscat
 */
public class UserExistException extends BusinessException {

    public UserExistException(String message) {
        super(message);
    }

}
