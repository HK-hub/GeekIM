package com.geek.im.common.exception;

import lombok.Data;

/**
 * @author : HK意境
 * @ClassName : CommonException
 * @date : 2024/8/2 10:02
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
public class CommonException extends RuntimeException {

    protected String code;

    private String desc;

    private String tip;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public CommonException() {
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CommonException(String message) {
        super(message);
    }
}
