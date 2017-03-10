package com.liferay.ds.workshop.task.server.storage;

import java.util.Collection;
import java.util.Optional;

import com.liferay.ds.workshop.task.server.api.Task;

public interface StorageProvider {
	Optional<Task> insert(Task task);

	Collection<Task> list();
}
