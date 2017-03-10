package com.liferay.ds.workshop.task.server.provider;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import com.liferay.ds.workshop.task.server.api.Task;
import com.liferay.ds.workshop.task.server.api.TaskServer;
import com.liferay.ds.workshop.task.server.config.TaskServerConfig;
import com.liferay.ds.workshop.task.server.config.TaskServerConfigMetaType;
import com.liferay.ds.workshop.task.server.config.TaskSystemConfigMetaType;
import com.liferay.ds.workshop.task.server.event.TaskEvent;
import com.liferay.ds.workshop.task.server.event.TaskServerListener;
import com.liferay.ds.workshop.task.server.storage.StorageProvider;

// @Designate(ocd = TaskServerConfigMetaType.class)
// @Component(immediate = true)
@Component(configurationPid = { "SystemConfig", "ServerConfig" }, immediate = true)
public class TaskManager implements TaskServer {

	@Override
	public Task insert(String name, String description) {
		Task task = new TaskImpl(String.valueOf(this.atomicLong.getAndIncrement()), name, description);

		Optional<Task> insertedTask = this.storageProvider.insert(task);

		/**
		 * for (TaskServerListener taskServerListener :
		 * this._taskServerListeners) {
		 * taskServerListener.notify(TaskEvent.CREATED); }
		 */
		for (TaskServerListener taskServerListener : this.serverListenerTracker.listeners()) {
			taskServerListener.notify(TaskEvent.CREATED);
		}

		return insertedTask.orElseGet(new Supplier<Task>() {

			@Override
			public Task get() {
				return new TaskImpl("NonExisting", "Non Existing", "NonExisting");
			}
		});
	}

	@Override
	public Collection<Task> list() {
		return this.storageProvider.list();
	}

	// @Activate
	void activate(BundleContext bundleContext) {
		System.out.println("Activating " + TaskManager.class);

		this.serverListenerTracker = new ServerListenerTracker(bundleContext);

		this.serverListenerTracker.start();
	}

	// @Activate
	void activateTypeSafeConfig(TaskServerConfig taskServerConfig) {
		System.out.println("Activating server in port " + taskServerConfig.port());
	}

	@Activate
	void activateWithMetaType(TaskSystemConfigMetaType systemConfig, TaskServerConfigMetaType serverConfig) {
		System.out.println("Activating server with Java" + systemConfig.java() + " at port " + serverConfig.port());
	}

	@Deactivate
	void deactivate() {
		System.out.println("Deactivating " + TaskManager.class);

		this.serverListenerTracker.stop();
	}

	@Reference(policyOption = ReferencePolicyOption.GREEDY, policy = ReferencePolicy.DYNAMIC)
	private volatile StorageProvider storageProvider;
	// @Reference(cardinality = ReferenceCardinality.AT_LEAST_ONE, policy =
	// ReferencePolicy.DYNAMIC)
	// private volatile List<TaskServerListener> _taskServerListeners = new
	// CopyOnWriteArrayList<>();
	private AtomicLong atomicLong = new AtomicLong(0);

	private ServerListenerTracker serverListenerTracker;

	private static class ServerListenerTracker
			implements ServiceTrackerCustomizer<TaskServerListener, TaskServerListener> {

		public ServerListenerTracker(BundleContext bundleContext) {
			this.bundleContext = bundleContext;
			this.serviceTracker = new ServiceTracker<>(bundleContext, TaskServerListener.class, this);
		}

		public List<TaskServerListener> listeners() {
			return this._listeners;
		}

		public void start() {
			this.serviceTracker.open();
		}

		public void stop() {
			this.serviceTracker.close();
		}

		@Override
		public TaskServerListener addingService(ServiceReference<TaskServerListener> reference) {

			TaskServerListener taskServerListener = this.bundleContext.getService(reference);

			this._listeners.add(taskServerListener);

			System.out.println("Tracking service " + taskServerListener);

			return taskServerListener;
		}

		@Override
		public void modifiedService(ServiceReference<TaskServerListener> reference, TaskServerListener service) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removedService(ServiceReference<TaskServerListener> reference, TaskServerListener service) {
			this._listeners.remove(service);

			this.bundleContext.ungetService(reference);

			System.out.println("Untracking service " + service);
		}

		private final BundleContext bundleContext;
		private final ServiceTracker<TaskServerListener, TaskServerListener> serviceTracker;
		private List<TaskServerListener> _listeners = new CopyOnWriteArrayList<>();
	}
}
