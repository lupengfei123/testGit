package com.example.demo.exception;

import com.example.demo.consts.ResultPacketCode;

/**
 * 权限Exception
 */
public class AdminPermissionDeniedException extends CustomException {

	public AdminPermissionDeniedException() {
	}

	public AdminPermissionDeniedException(String message) {
		super( ResultPacketCode.APIResultCode.AuthFail.getCode(), message);
	}
}
