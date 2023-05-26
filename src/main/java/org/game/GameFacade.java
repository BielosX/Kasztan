package org.game;

import org.game.keys.GameKeyEvent;
import org.game.keys.GameKeyVisitor;
import org.game.keys.KeyPressed;
import org.game.keys.KeyReleased;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.awt.event.KeyEvent;

@Service
public class GameFacade {

    private final GameKeyEventVisitor visitor = new GameKeyEventVisitor();
    public float rectX = 100.0f;
    public float rectY = 100.0f;
    private float horizontalVelocity = 0.0f;
    private float verticalVelocity = 0.0f;

    @EventListener
    public void handleKeyEvent(GameKeyEvent event) {
        event.accept(visitor);
    }

    public void tick(float seconds) {
        rectX += horizontalVelocity * seconds;
        rectY += verticalVelocity * seconds;
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
