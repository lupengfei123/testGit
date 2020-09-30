package com.example.demo.exception;
/** 
 * 手动回滚事物Exception
 */
public class TransactionRollBackException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3641419721621829296L;

	/** 
     *  
     */  
    public TransactionRollBackException(){  
        super();  
    }  
    
    /** 
     *  
     * @param message 
     */  
    public TransactionRollBackException(String message){  
    	super(message);
    }  
}
