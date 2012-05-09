package se.chalmers.tda367.std.utilities;

import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * A convenience class for creating objects using dependency injection.
 * @author Emil Edholm
 * @date   Apr 28, 2012
 */
public final class SpriteCreator {
	private SpriteCreator(){}
	private static Class<? extends NativeSprite> nativeSpriteClass;
	
	public static Sprite create(String resourceString) {
		if(nativeSpriteClass == null) {
			Logger.getLogger("se.chalmers.tda367.std.utilities").severe("Unable to bind sprite. Native sprite class not set.");
			return null;
		}
		Injector spriteFactory = Guice.createInjector(new SpriteModule(nativeSpriteClass));
		SpriteFactory factory = spriteFactory.getInstance(SpriteFactory.class);
		return factory.create(resourceString);
	}
	
	/**
	 * Used to set what native sprite class should be used. This is usually set only once in the main method.
	 * @param nativeClass - the class of the native sprite to use to bind to the sprite.
	 */
	public static void setNativeSpriteClass(Class<? extends NativeSprite> nativeClass) {
		nativeSpriteClass = nativeClass;
	}
}
