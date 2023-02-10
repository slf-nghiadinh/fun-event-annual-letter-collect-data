package com.sunlife.vn.common.exception;

import lombok.Getter;

@Getter
public class TerminateException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -7904826838540750130L;
    
    private final ErrorCode code;
    
    public TerminateException(ErrorCode code) {
        super();
        this.code = code;
    }
    
    public TerminateException(ErrorCode code, Throwable cause) {
        super(code.getDescription());
        this.code = code;
    }
    
    public TerminateException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }
    
    public TerminateException(ErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
