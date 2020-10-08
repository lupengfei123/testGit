package com.example.demo.exception;


import com.example.demo.consts.ResultPacketCode;

/**
 * 权限Exception
 */
public class PermissionDeniedException extends CustomException {

	public  PermissionDeniedException() {
	}

	public PermissionDeniedException(String message) {
		super(ResultPacketCode.APIResultCode.AuthFail.getCode(), message);
	}
}
