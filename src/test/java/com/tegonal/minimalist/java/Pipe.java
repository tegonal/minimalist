package com.tegonal.variist.java;

import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

public class Pipe<T> {
	private final T t;

	public Pipe(T t) {
		this.t = t;
	}

	public <R> Pipe<R> pipe(@NotNull Function1<T, R> f) {
		return new Pipe<>(f.invoke(t));
	}
}
