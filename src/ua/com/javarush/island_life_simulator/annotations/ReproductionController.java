package ua.com.javarush.island_life_simulator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReproductionController {
    int minAmount() default 10;
    int maxAmount() default 1000;
}
