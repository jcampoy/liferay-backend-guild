package com.liferay.ds.workshop.task.server.config;

import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "ServerConfig")
public @interface TaskServerConfigMetaType {
	int port() default 3000;
}
