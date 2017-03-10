package com.liferay.ds.workshop.task.file.event;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.liferay.ds.workshop.task.server.event.TaskEvent;
import com.liferay.ds.workshop.task.server.event.TaskServerListener;

@Component
public class LogServerListener implements TaskServerListener {

	@Override
	public void notify(TaskEvent taskEvent) {
		System.out.println("Event " + taskEvent + " received");

	}

	@Activate
	void activate() {
		System.out.println("Activating " + LogServerListener.class);
	}
}
