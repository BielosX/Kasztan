package org.level;

import com.google.gson.Gson;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Configuration
@ComponentScan("org.level")
@PropertySource("classpath:application.properties")
public class LevelConfiguration {

    @Value("${levelFile}")
    private String levelFile;
    @Value("${game.gravity}")
    private float gravity;

    private final Gson gson = new Gson();

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public LevelSpecification levelSpecification() throws IOException {
        ClassPathResource resource = new ClassPathResource(levelFile);
        Reader reader = new InputStreamReader(resource.getInputStream());
        return gson.fromJson(reader, LevelSpecification.class);
    }

    @Bean
    public Player player(LevelSpecification specification) {
        RealVector velocity = new ArrayRealVector(2, 0.0);
        return new Player(specification.player().x(), specification.player().y(), velocity);
    }
}
