package se.chalmers.tda367.std.utilities;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * A convenience class for creating objects using dependency injection.
 * @author Emil Edholm
 * @date   Apr 28, 2012
 */
public final class SpriteCreator {
	private SpriteCreator(){}
	
	public static Sprite create(String resourceString) {
		Injector spriteFactory = Guice.createInjector(new SpriteModule());
		SpriteFactory factory = spriteFactory.getInstance(SpriteFactory.class);
		return factory.create(resourceString);
	}
}
