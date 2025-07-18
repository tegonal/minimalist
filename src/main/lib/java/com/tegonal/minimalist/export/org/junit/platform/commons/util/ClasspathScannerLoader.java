/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package com.tegonal.minimalist.export.org.junit.platform.commons.util;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

import java.util.List;
import java.util.ServiceLoader;

import org.junit.platform.commons.JUnitException;
import org.junit.platform.commons.support.scanning.ClasspathScanner;
import org.junit.platform.commons.support.scanning.DefaultClasspathScanner;

/**
 * @since 1.12
 */
class ClasspathScannerLoader {

	static ClasspathScanner getInstance() {
		ServiceLoader<ClasspathScanner> serviceLoader = ServiceLoader.load(ClasspathScanner.class,
			ClassLoaderUtils.getDefaultClassLoader());

		List<ClasspathScanner> classpathScanners = stream(serviceLoader.spliterator(), false).collect(toList());

		if (classpathScanners.size() == 1) {
			return classpathScanners.get(0);
		}

		if (classpathScanners.size() > 1) {
			throw new JUnitException(String.format(
				"There should not be more than one ClasspathScanner implementation present on the classpath but there were %d: %s",
				classpathScanners.size(), classpathScanners));
		}

		return new DefaultClasspathScanner(ClassLoaderUtils::getDefaultClassLoader, ReflectionUtils::tryToLoadClass);
	}

}
