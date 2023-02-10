package com.sunlife.vn.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	EVT_DATA_NOT_UPDATED("Event Data Can not be updated");
	private final String description;

	private ErrorCode(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return name();
	}
}
