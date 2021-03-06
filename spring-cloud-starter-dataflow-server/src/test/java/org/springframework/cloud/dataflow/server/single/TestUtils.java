/*
 * Copyright 2016-2017 the original author or authors.
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

package org.springframework.cloud.dataflow.server.single;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.util.ReflectionUtils;

/**
 * Utils for tests.
 *
 * @author Janne Valkealahti
 * @author Soby Chacko
 */
public class TestUtils {

	@SuppressWarnings("unchecked")
	public static <T> T readField(String name, Object target) throws Exception {
		Field field = null;
		Class<?> clazz = target.getClass();
		do {
			try {
				field = clazz.getDeclaredField(name);
			}
			catch (Exception ex) {
			}

			clazz = clazz.getSuperclass();
		}
		while (field == null && !clazz.equals(Object.class));

		if (field == null)
			throw new IllegalArgumentException(
					"Cannot find field '" + name + "' in the class hierarchy of " + target.getClass());
		field.setAccessible(true);
		return (T) field.get(target);
	}

	@SuppressWarnings("unchecked")
	public static <T> T callMethod(String name, Object target) throws Exception {
		Class<?> clazz = target.getClass();
		Method method = ReflectionUtils.findMethod(clazz, name);

		if (method == null)
			throw new IllegalArgumentException(
					"Cannot find method '" + method + "' in the class hierarchy of " + target.getClass());
		method.setAccessible(true);
		return (T) ReflectionUtils.invokeMethod(method, target);
	}

	public static void setField(String name, Object target, Object value) throws Exception {
		Field field = null;
		Class<?> clazz = target.getClass();
		do {
			try {
				field = clazz.getDeclaredField(name);
			}
			catch (Exception ex) {
			}

			clazz = clazz.getSuperclass();
		}
		while (field == null && !clazz.equals(Object.class));

		if (field == null)
			throw new IllegalArgumentException(
					"Cannot find field '" + name + "' in the class hierarchy of " + target.getClass());
		field.setAccessible(true);
		field.set(target, value);
	}

	@SuppressWarnings("unchecked")
	public static <T> T callMethod(String name, Object target, Object[] args, Class<?>[] argsTypes) throws Exception {
		Class<?> clazz = target.getClass();
		Method method = ReflectionUtils.findMethod(clazz, name, argsTypes);

		if (method == null)
			throw new IllegalArgumentException(
					"Cannot find method '" + method + "' in the class hierarchy of " + target.getClass());
		method.setAccessible(true);
		return (T) ReflectionUtils.invokeMethod(method, target, args);
	}

	public static Map<String, String> toImmutableMap(
			String... entries) {
		if(entries.length % 2 == 1)
			throw new IllegalArgumentException("Entries should contain even number of values");
		return Collections.unmodifiableMap(IntStream.range(0, entries.length / 2).map(i -> i * 2)
				.collect(HashMap::new,
						(m, i) -> m.put(entries[i], entries[i + 1]),
						Map::putAll));
	}

}
