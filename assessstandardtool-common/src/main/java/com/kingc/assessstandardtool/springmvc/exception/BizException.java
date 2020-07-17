package com.kingc.assessstandardtool.springmvc.exception;


/**
 * @author Administrator
 */
public class BizException extends RuntimeException {

    /**
     * TODO: 2018/10/8 此异常向下拓展为异常体系
     */
    public BizException(String message) {
        super(message);
    }
}
