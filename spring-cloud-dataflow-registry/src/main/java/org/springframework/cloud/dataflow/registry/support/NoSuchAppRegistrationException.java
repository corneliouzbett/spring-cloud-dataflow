/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.cloud.dataflow.registry.support;

import org.springframework.cloud.dataflow.core.AppRegistration;
import org.springframework.cloud.dataflow.core.ApplicationType;

/**
 * Thrown when an {@link AppRegistration} of a given name and {@link ApplicationType} was
 * expected but did not exist.
 *
 * @author Gunnar Hillert
 */
public class NoSuchAppRegistrationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchAppRegistrationException(String name, ApplicationType type) {
		super(String.format("The '%s:%s' application could not be found.", type, name));
	}

	public NoSuchAppRegistrationException(String name, ApplicationType type, String version) {
		super(String.format("The '%s:%s:%s' application could not be found.", type, name, version));
	}
}
