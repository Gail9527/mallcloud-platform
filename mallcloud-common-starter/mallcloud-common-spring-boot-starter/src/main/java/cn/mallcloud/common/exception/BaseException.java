package cn.mallcloud.common.exception;

/**
 * 基本异常.
 *
 * @author zscat
 */
class BaseException extends RuntimeException {

    BaseException() {
        super();
    }

    BaseException(String message) {
        super(message);
    }

    BaseException(Throwable cause) {
        super(cause);
    }

    BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}