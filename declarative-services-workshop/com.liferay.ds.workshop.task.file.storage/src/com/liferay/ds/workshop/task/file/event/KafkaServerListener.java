package com.liferay.ds.workshop.task.file.event;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.liferay.ds.workshop.task.server.event.TaskEvent;
import com.liferay.ds.workshop.task.server.event.TaskServerListener;

@Component(enabled = false, immediate = true)
public class KafkaServerListener implements TaskServerListener {

	@Override
	public void notify(TaskEvent taskEvent) {
		System.out.println("Sending the event " + taskEvent + " to a Kafka topic");
	}

	@Activate
	void activate() {
		System.out.println("Activating " + KafkaServerListener.class);
	}
}
