package org.game;

import org.game.keys.GameKeyEvent;
import org.game.keys.GameKeyVisitor;
import org.game.keys.KeyPressed;
import org.game.keys.KeyReleased;
import org.level.LevelConfiguration;
import org.level.LevelFacade;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Service;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Map;

@Service
public class GameFacade {

    private static final String CUSTOM_PROPERTIES_NAME = "CUSTOM_PROPERTIES";
    private static final String LEVEL_FILE_PROPERTY_NAME = "levelFile";
    private final GameKeyEventVisitor visitor = new GameKeyEventVisitor();
    private float horizontalVelocity = 0.0f;
    private float verticalVelocity = 0.0f;
    private final AnnotationConfigApplicationContext levelContext;

    public GameFacade() {
        levelContext = new AnnotationConfigApplicationContext();
        levelContext.register(LevelConfiguration.class);
        ConfigurableEnvironment environment = new StandardEnvironment();
        Map<String,Object> properties = Map.of(LEVEL_FILE_PROPERTY_NAME, "level/level1.json");
        environment.getPropertySources().addFirst(new MapPropertySource(CUSTOM_PROPERTIES_NAME, properties));
        levelContext.setEnvironment(environment);
        levelContext.refresh();
    }

    @EventListener
    public void handleKeyEvent(GameKeyEvent event) {
        event.accept(visitor);
    }

    public void tick(float seconds) {
    }

    public void drawLevel(Graphics2D graphics2D) {
        levelContext.getBean(LevelFacade.class).draw(graphics2D);
    }

    private class GameKeyEventVisitor implements GameKeyVisitor {

        @Override
        public void visit(KeyPressed key) {
            if (key.keyCode() == KeyEvent.VK_D) {
                horizontalVelocity = 30.0f;
            }
            if (key.keyCode() == KeyEvent.VK_A) {
                horizontalVelocity = -30.0f;
            }
            if (key.keyCode() == KeyEvent.VK_W) {
                verticalVelocity = -30.0f;
            }
            if (key.keyCode() == KeyEvent.VK_S) {
                verticalVelocity = 30.0f;
            }
        }

        @Override
        public void visit(KeyReleased key) {
            if (key.keyCode() == KeyEvent.VK_D || key.keyCode() == KeyEvent.VK_A) {
                horizontalVelocity = 0.0f;
            }
            if (key.keyCode() == KeyEvent.VK_W || key.keyCode() == KeyEvent.VK_S) {
                verticalVelocity = 0.0f;
            }
        }
    }
}
