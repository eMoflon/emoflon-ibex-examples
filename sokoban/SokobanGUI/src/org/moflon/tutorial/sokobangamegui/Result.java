package org.moflon.tutorial.sokobangamegui;

public class Result {
	private boolean success;
	private String reasonForFailure;

	public Result() {
		success = true;
	}

	public Result(String reasonForFailure) {
		success = false;
		this.reasonForFailure = reasonForFailure;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getReason() {
		return reasonForFailure;
	}
}
