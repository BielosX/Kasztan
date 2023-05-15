package org.game;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("org.game")
@PropertySource("classpath:application.properties")
public class GameConfiguration {

    @Value("${window.width}")
    private int windowWidth;
    @Value("${window.height}")
    private int windowHeight;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public WindowConfig windowConfig() {
        return new WindowConfig(windowWidth, windowHeight);
    }
}