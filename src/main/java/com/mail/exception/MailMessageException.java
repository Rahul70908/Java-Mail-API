package com.mail.exception;

public class MailMessageException extends RuntimeException{

	private static final long serialVersionUID = -7773947089580008560L;

	public MailMessageException() {
		super();
	}

	public MailMessageException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MailMessageException(String message, Throwable cause) {
		super(message, cause);
	}

	public MailMessageException(String message) {
		super(message);
	}

	public MailMessageException(Throwable cause) {
		super(cause);
	}
}