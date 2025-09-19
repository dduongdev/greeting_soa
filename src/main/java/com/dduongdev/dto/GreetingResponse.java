package com.dduongdev.dto;

public class GreetingResponse {
	private String name;
	private final String CONTENT_TEMPLATE = "Hello %s!";

	public GreetingResponse(String name) {
		this.name = name;
	}

	public String getContent() {
		return String.format(CONTENT_TEMPLATE, this.name);
	}

	public void setContent(String content) {
		this.name = content;
	}

}
