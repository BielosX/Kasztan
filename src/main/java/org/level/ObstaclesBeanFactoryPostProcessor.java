package org.level;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.stream.IntStream;

public class ObstaclesBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {
    private final static String LEVEL_FILE_PROPERTY = "levelFile";
    private Environment environment;
    private final Gson gson = new Gson();

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    @SneakyThrows
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        String levelFile = environment.getProperty(LEVEL_FILE_PROPERTY);
        assert levelFile != null;
        ClassPathResource resource = new ClassPathResource(levelFile);
        Reader reader = new InputStreamReader(resource.getInputStream());
        LevelSpecification specification = gson.fromJson(reader, LevelSpecification.class);
        IntStream.range(0, specification.obstacles().size())
                .forEach(index -> {
                    String beanName = "obstacle" + index;
                    LevelSpecification.ObstacleSpecification obstacle = specification.obstacles().get(index);
                    BeanDefinition definition = BeanDefinitionBuilder.genericBeanDefinition(Obstacle.class)
                            .addConstructorArgValue(obstacle.x())
                            .addConstructorArgValue(obstacle.y())
                            .addConstructorArgValue(obstacle.width())
                            .addConstructorArgValue(obstacle.height())
                            .getBeanDefinition();
                    registry.registerBeanDefinition(beanName, definition);
                });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {}
}
