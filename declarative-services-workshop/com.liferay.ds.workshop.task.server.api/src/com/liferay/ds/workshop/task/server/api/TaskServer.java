package com.liferay.ds.workshop.task.server.api;

import java.util.Collection;

public interface TaskServer {
	public Task insert(String name, String description);

	public Collection<Task> list();
}