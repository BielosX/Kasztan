package org.level;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

public class LevelBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean instanceof GameObject gameObject) {
            System.out.println("GameObject " +
                    beanName +
                    " initiated ad position (" +
                    gameObject.getPositionX() +
                    ", " +
                    gameObject.getPositionY() +
                    ")");
        }
        return true;
    }
}
