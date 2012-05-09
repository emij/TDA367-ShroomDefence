package se.chalmers.tda367.std.utilities;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Used to inject the resource string into a sprite (for use in Google Guice).
 * @author Emil Edholm
 * @date   Apr 28, 2012
 */
final class SpriteModule extends AbstractModule {
	private final Class<? extends NativeSprite> nativeClass;
	public SpriteModule(Class<? extends NativeSprite> nativeClass) {
		this.nativeClass = nativeClass;
	}
	@Override
	public void configure() {
		bind(NativeSprite.class).to(nativeClass);
		install(new FactoryModuleBuilder()
	     .implement(Sprite.class, Sprite.class)
	     .build(SpriteFactory.class));
	}
	
	

}
