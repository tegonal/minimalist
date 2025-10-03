module com.tegonal.minimalist {
	requires transitive kotlin.stdlib;
	requires transitive kotlin.reflect;
	requires org.junit.jupiter.params;
	requires org.junit.platform.commons;
	requires ch.tutteli.kbox;
	requires java.logging;
	// from the export of junit classes
	requires org.apiguardian.api;

	exports com.tegonal.minimalist;
	exports com.tegonal.minimalist.config;
	exports com.tegonal.minimalist.generators;
	exports com.tegonal.minimalist.providers;
	exports com.tegonal.minimalist.utils;
}
