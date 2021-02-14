package com.kaohe.project.sysconfig.exception;

public class SysException extends Exception {
	private static final long serialVersionUID = 1L;

	public SysException(String message) {
		super(message);
	}

	public SysException(String message, Throwable cause) {
		super(message, cause);
	}
}
