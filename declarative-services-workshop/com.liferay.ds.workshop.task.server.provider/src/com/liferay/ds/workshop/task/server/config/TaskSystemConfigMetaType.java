package com.liferay.ds.workshop.task.server.config;

import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "SystemConfig")
public @interface TaskSystemConfigMetaType {
	String java() default "1.8";
}
