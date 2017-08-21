package com.vip.yyl.web.rest.exceptions;

import java.io.IOException;

/**
 * Created by santoshm1 on 06/06/14.
 */
public class ApiException extends RuntimeException {

    public ApiException(String message, Throwable cause) {
	super(message, cause);
    }

    public ApiException(IOException e) {
	super(e);
    }

}
