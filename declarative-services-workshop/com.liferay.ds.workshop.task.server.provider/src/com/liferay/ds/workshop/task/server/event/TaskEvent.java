package com.liferay.ds.workshop.task.server.event;

public enum TaskEvent {
	CREATED("Task created");

	TaskEvent(String description) {
		this.description = description;
	}

	private String description;
}
