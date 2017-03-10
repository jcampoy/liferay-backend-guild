package com.liferay.ds.workshop.task.server.command;

import java.util.Collection;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.ds.workshop.task.server.api.Task;
import com.liferay.ds.workshop.task.server.api.TaskServer;

@Component(immediate = true, property = { "osgi.command.function=insert", "osgi.command.function=list",
		"osgi.command.scope=tasks" }, service = TasksCommands.class)
public class TasksCommands {

	public void insert(String name, String description) {
		Task task = this.taskServer.insert(name, description);
		System.out.println(task);
	}

	public void list() {
		Collection<Task> tasks = this.taskServer.list();

		for (Task task : tasks) {
			System.out.println(task);
		}
	}

	@Activate
	void activate() {
		//this.taskServer.insert("A", "My A Task");
		//this.taskServer.insert("B", "My B Task");
	}

	@Reference
	private TaskServer taskServer;

}
