package com.liferay.ds.workshop.task.lazy.storage;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.liferay.ds.workshop.task.server.event.TaskEvent;
import com.liferay.ds.workshop.task.server.event.TaskServerListener;

@Component
public class LazyStorage implements TaskServerListener {

	@Override
	public void notify(TaskEvent taskEvent) {
		// TODO Auto-generated method stub
		
	}

	@Activate
	void activate() {
		System.out.println("Activating " + LazyStorage.class);
	}

}
