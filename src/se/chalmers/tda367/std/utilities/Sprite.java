package se.chalmers.tda367.std.utilities;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Represents a Sprite. i.e. a moving (or static) image of sorts.
 * <br /> Note that this class <b>must</b> be created by the Guice injector, otherwise it will throw {@code NullPointerException}.
 * @author Emil Edholm
 * @date Apr 24, 2012
 */
public class Sprite {
	private final Path imagePath;
	private NativeSprite nativeSprite; // Uses Guice dependency injection to load the right class.
	
	/**
	 * Create a sprite with the image loaded from a resource string.
	 * @param nativeSprite - the native sprite to use (dependency injected).
	 * @param resourceString - the resource string from which to load the image.
	 * @throws URISyntaxException - if unable to fetch and convert the {@code resourceString} to a correct URI.
	 */
	@Inject
	public Sprite(NativeSprite nativeSprite, @Assisted("resourceString") String resourceString) throws URISyntaxException{
		this.nativeSprite = nativeSprite;
		URI uri = getClass().getResource(resourceString).toURI();
		if(uri != null) {
			imagePath = Paths.get(uri);
			nativeSprite.create(imagePath.toAbsolutePath());
		}
		else {
			Logger.getLogger("se.chalmers.tda367.std.utilities").severe("Unable to find the resource: " + resourceString);
			imagePath = null;
		}
		
	}
	
	/**
	 * Retrieves the path associated with this sprite.
	 * @return a path to the image contained within this sprite.
	 */
	public Path getImagePath() {
		return imagePath;
	}
	
	/**
	 * Retrieve the native sprite that does the actual drawing.
	 * @return the native sprite attached to this sprite.
	 */
	public NativeSprite getNativeSprite() {
		return nativeSprite;
	}
}
