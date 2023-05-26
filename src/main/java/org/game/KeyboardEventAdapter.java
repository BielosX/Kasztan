package org.game;

import lombok.RequiredArgsConstructor;
import org.game.keys.KeyPressed;
import org.game.keys.KeyReleased;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Component
@RequiredArgsConstructor
public class KeyboardEventAdapter implements KeyListener {
    private final ApplicationEventPublisher publisher;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        publisher.publishEvent(new KeyPressed(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        publisher.publishEvent(new KeyReleased(e.getKeyCode()));
    }
}
