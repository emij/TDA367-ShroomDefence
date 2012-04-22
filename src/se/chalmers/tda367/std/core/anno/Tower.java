package se.chalmers.tda367.std.core.anno;

import java.lang.annotation.*;

/**
 * Annotation used to describe a defensive tower and it's strength relative to 
 * the enemies.
 * @author Emil Edholm
 * @date   Apr 22, 2012
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tower {
	String name() default "Unamed tower";
	String description() default "No description given";
	double towerStrength();
}
