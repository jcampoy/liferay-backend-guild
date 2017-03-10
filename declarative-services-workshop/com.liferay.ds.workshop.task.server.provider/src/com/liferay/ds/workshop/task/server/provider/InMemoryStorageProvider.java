package com.liferay.ds.workshop.task.server.provider;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import com.liferay.ds.workshop.task.server.api.Task;
import com.liferay.ds.workshop.task.server.storage.StorageProvider;

@Component
public class InMemoryStorageProvider implements StorageProvider {

	@Override
	public Optional<Task> insert(Task task) {
		this.storage.putIfAbsent(task.id(), task);

		return Optional.of(task);
	}

	@Override
	public Collection<Task> list() {
		return storage.values();
	}

	@Activate
	void activate() {
		System.out.println("Activating " + InMemoryStorageProvider.class);
	}

	@Deactivate
	void deactivate() {
		System.out.println("Deactivating " + InMemoryStorageProvider.class);
	}

	private ConcurrentHashMap<String, Task> storage = new ConcurrentHashMap<>();

}
