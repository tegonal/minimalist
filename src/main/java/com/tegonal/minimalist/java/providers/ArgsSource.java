package com.tegonal.minimalist.java.providers;

import com.tegonal.minimalist.providers.ArgsArgumentProvider;
import com.tegonal.minimalist.providers.ArgsSourceLike;
import org.apiguardian.api.API;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

import static org.apiguardian.api.API.Status.STABLE;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@API(status = STABLE, since = "5.7")
@ArgumentsSource(ArgsArgumentProvider.class)
@ArgsSourceLike
public @interface ArgsSource {
	@Language("jvm-method-name")
	String methodName();
}
