package org.game;

import org.springframework.stereotype.Service;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.Queue;

@Service
public class KeyboardEventPump implements KeyListener {

    private static final int INIT_SIZE = 16;

    private Queue<KeyEvent> events;

    public KeyboardEventPump() {
        this.events = new ArrayDeque<>(INIT_SIZE);
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

    public Queue<KeyEvent> getEvents() {
        Queue<KeyEvent> waitingEvents = events;
        events = new ArrayDeque<>(INIT_SIZE);
        return waitingEvents;
    }
}
