package org.game;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
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
    private static final RealVector ZERO_VELOCITY = new ArrayRealVector(2, 0.0);
    private final GameKeyEventVisitor visitor = new GameKeyEventVisitor();
    private final AnnotationConfigApplicationContext levelContext;
    private final LevelFacade levelFacade;

    public GameFacade() {
        levelContext = new AnnotationConfigApplicationContext();
        levelContext.register(LevelConfiguration.class);
        ConfigurableEnvironment environment = new StandardEnvironment();
        Map<String,Object> properties = Map.of(LEVEL_FILE_PROPERTY_NAME, "level/level1.json");
        environment.getPropertySources().addFirst(new MapPropertySource(CUSTOM_PROPERTIES_NAME, properties));
        levelContext.setEnvironment(environment);
        levelContext.refresh();
        levelFacade = levelContext.getBean(LevelFacade.class);
    }

    @EventListener
    public void handleKeyEvent(GameKeyEvent event) {
        event.accept(visitor);
    }

    public void tick(float deltaSeconds) {
        levelFacade.tick(deltaSeconds);
    }

    public void drawLevel(Graphics2D graphics2D) {
        levelFacade.draw(graphics2D);
    }

    private class GameKeyEventVisitor implements GameKeyVisitor {

        @Override
        public void visit(KeyPressed key) {
            if (key.keyCode() == KeyEvent.VK_D) {
                RealVector vector = new ArrayRealVector(new double[]{30.0, 0.0});
                levelFacade.setPlayerVelocity(vector);
            }
            if (key.keyCode() == KeyEvent.VK_A) {
                RealVector vector = new ArrayRealVector(new double[]{-30.0, 0.0});
                levelFacade.setPlayerVelocity(vector);
            }
            if (key.keyCode() == KeyEvent.VK_W) {
                RealVector vector = new ArrayRealVector(new double[]{0.0, -30.0});
                levelFacade.setPlayerVelocity(vector);
            }
            if (key.keyCode() == KeyEvent.VK_S) {
                RealVector vector = new ArrayRealVector(new double[]{0.0, 30.0});
                levelFacade.setPlayerVelocity(vector);
            }
        }

        @Override
        public void visit(KeyReleased key) {
            if (key.keyCode() == KeyEvent.VK_D || key.keyCode() == KeyEvent.VK_A) {
                levelFacade.setPlayerVelocity(ZERO_VELOCITY);
            }
            if (key.keyCode() == KeyEvent.VK_W || key.keyCode() == KeyEvent.VK_S) {
                levelFacade.setPlayerVelocity(ZERO_VELOCITY);
            }
        }
    }
}
