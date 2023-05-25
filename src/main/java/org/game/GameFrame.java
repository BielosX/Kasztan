package org.game;

import org.springframework.stereotype.Service;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;


@Service
public class GameFrame extends Frame {
    private static final int BUFFER_TYPE = BufferedImage.TYPE_INT_RGB;

    private final KeyboardEventPump keyboardEventPump;
    private int rectX;
    private int rectY;
    private BufferedImage secondBuffer;

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
        addComponentListener(new ResizeListener());
        addKeyListener(keyboardEventPump);
        this.keyboardEventPump = keyboardEventPump;
        rectX = 200;
        rectY = 200;
        secondBuffer = new BufferedImage(config.width(), config.height(), BUFFER_TYPE);
    }

    private void secondBufferClearColor(int color) {
        int width = secondBuffer.getWidth();
        int height = secondBuffer.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                secondBuffer.setRGB(x, y, color);
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        keyboardEventPump.getEvents().forEach(e -> {
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
        secondBufferClearColor(0);
        Graphics2D bufferGraphics = secondBuffer.createGraphics();
        bufferGraphics.drawString("Hello", 100, 100);
        bufferGraphics.drawRect(rectX, rectY, 30, 30);
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.drawImage(secondBuffer, null, 0, 0);
        repaint();
    }

    private class ResizeListener implements ComponentListener {

        @Override
        public void componentResized(ComponentEvent e) {
            Component component = e.getComponent();
            secondBuffer = new BufferedImage(component.getWidth(), component.getHeight(), BUFFER_TYPE);
        }

        @Override
        public void componentMoved(ComponentEvent e) {}
        @Override
        public void componentShown(ComponentEvent e) {}
        @Override
        public void componentHidden(ComponentEvent e) {}
    }
}
