package com.intransition.labs.editor;

import java.util.ArrayList;
import java.util.List;

public class Alert {
	
	private AlertLevel level;
	
	private String description; 
	
	private boolean show = true;
	
	private List<String> messages = new ArrayList<String>();
	
	private String title;
	
	public boolean hasErrors;

	public AlertLevel getLevel() {
		return level;
	}

	public void setLevel(AlertLevel level) {
		this.level = level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	public boolean hasErrors() {
		return hasErrors;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
