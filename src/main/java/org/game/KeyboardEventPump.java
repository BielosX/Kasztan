package org.game;

import org.springframework.stereotype.Service;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class KeyboardEventPump implements KeyListener {

    private final ConcurrentLinkedQueue<KeyEvent> events;

    public KeyboardEventPump() {
        this.events = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        events.add(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        events.add(e);
    }

    public Optional<KeyEvent> getEvent() {
        return Optional.ofNullable(events.poll());
    }
}
