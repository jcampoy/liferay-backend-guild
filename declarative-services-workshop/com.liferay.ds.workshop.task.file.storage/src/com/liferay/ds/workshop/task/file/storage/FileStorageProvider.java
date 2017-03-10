package com.liferay.ds.workshop.task.file.storage;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import com.liferay.ds.workshop.task.server.api.Task;
import com.liferay.ds.workshop.task.server.storage.StorageProvider;

@Component(property = Constants.SERVICE_RANKING + ":Integer=10", enabled = false , immediate = true)
public class FileStorageProvider implements StorageProvider {

	@Override
	public Optional<Task> insert(Task task) {
		return Optional.empty();
	}

	@Override
	public Collection<Task> list() {
		System.out.println("Executing list() in " + FileStorageProvider.class);
		return Collections.emptyList();
	}

}
