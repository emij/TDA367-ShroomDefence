package se.chalmers.tda367.std.core.anno;

import java.lang.annotation.*;

/**
 * An annotation used to describe an enemy and it's strength.
 * @author Emil Edholm
 * @date   Apr 22, 2012
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Enemy {
	String name() default "Unnamed enemy";
	String description() default "No description given";
	double enemyStrength();
}
