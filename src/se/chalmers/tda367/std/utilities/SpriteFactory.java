package se.chalmers.tda367.std.utilities;

import com.google.inject.assistedinject.Assisted;

/**
 * Represents a sprite factory interface for use with Guice Dependency Injection.
 * @author Emil Edholm
 * @date   Apr 28, 2012
 */
interface SpriteFactory {
	public Sprite create(@Assisted("resourceString") String resourceString);
}
