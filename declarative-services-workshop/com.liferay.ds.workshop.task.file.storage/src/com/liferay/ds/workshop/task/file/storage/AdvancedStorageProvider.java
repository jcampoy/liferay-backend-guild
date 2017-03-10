package com.liferay.ds.workshop.task.file.storage;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;

import com.liferay.ds.workshop.task.server.api.Task;
import com.liferay.ds.workshop.task.server.storage.StorageProvider;

@Component(property = "service.ranking=20", enabled = false)
public class AdvancedStorageProvider implements StorageProvider {

	@Override
	public Optional<Task> insert(Task task) {
		return Optional.empty();
	}

	@Override
	public Collection<Task> list() {
		System.out.println("Executing list() in " + AdvancedStorageProvider.class);
		return Collections.emptyList();
	}

}
