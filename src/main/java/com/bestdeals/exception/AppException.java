package com.bestdeals.exception;

import java.util.ResourceBundle;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 6793937610493311162L;

	public AppException(String msg, ResourceBundle bundle) {
		super(bundle.getString(msg));
	}

}
