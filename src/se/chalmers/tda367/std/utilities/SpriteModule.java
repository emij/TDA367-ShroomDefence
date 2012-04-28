package se.chalmers.tda367.std.utilities;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Used to inject the resource string into a sprite (for use in Google Guice).
 * @author Emil Edholm
 * @date   Apr 28, 2012
 */
final class SpriteModule extends AbstractModule {
	@Override
	public void configure() {
		install(new FactoryModuleBuilder()
	     .implement(Sprite.class, Sprite.class)
	     .build(SpriteFactory.class));
	}

}
