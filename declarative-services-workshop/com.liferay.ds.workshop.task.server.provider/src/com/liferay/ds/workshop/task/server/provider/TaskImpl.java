package com.liferay.ds.workshop.task.server.provider;

import com.liferay.ds.workshop.task.server.api.Task;

public class TaskImpl implements Task {

	public TaskImpl(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	@Override
	public String id() {
		return this.id;
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public String description() {
		return this.description;
	}

	@Override
	public String toString() {
		return "Task: " + this.name + ": " + this.description;
	}

	private final String id;
	private final String name;
	private final String description;

}
