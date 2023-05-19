package org.game;

import org.springframework.stereotype.Service;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Optional;


@Service
public class GameFrame extends Frame {

    private final KeyboardEventPump keyboardEventPump;
    private int rectX;
    private int rectY;

    public GameFrame(WindowConfig config, KeyboardEventPump keyboardEventPump) {
        setSize(config.width(), config.height());
        if (config.fullscreen()) {
            setExtendedState(Frame.MAXIMIZED_BOTH);
            setUndecorated(true);
        }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(keyboardEventPump);
        this.keyboardEventPump = keyboardEventPump;
        rectX = 200;
        rectY = 200;
    }

    @Override
    public void paint(Graphics graphics) {
        Optional<KeyEvent> event = keyboardEventPump.getEvent();
        event.ifPresent(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_A) {
                rectX -= 20;
            }
            if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_D) {
                rectX += 20;
            }
            if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_S) {
                rectY += 20;
            }
            if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_W) {
                rectY -= 20;
            }
        });
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.drawString("Hello", 100, 100);
        graphics2d.drawRect(rectX, rectY, 30, 30);
        repaint();
    }
}
