package com.sinan.javademo.apiapplication.exception;

/**
 * An exception happens when a specific parameter is missing when calling an API.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public class RequiredParamException extends APIException {
    private final static String messageTemplate = "Param [%s] is required!";
    private final String paramName;

    public RequiredParamException(String paramName) {
        super(String.format(messageTemplate, paramName));
        this.paramName = paramName;
    }
}
