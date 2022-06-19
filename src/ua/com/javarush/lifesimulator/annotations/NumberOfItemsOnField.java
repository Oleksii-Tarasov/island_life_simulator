package ua.com.javarush.lifesimulator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberOfItemsOnField {
    int minAmount() default 1;
    int maxAmount() default 15;
    int maxAmountOnCell() default 5;
}
