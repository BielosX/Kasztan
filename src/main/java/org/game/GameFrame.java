package org.game;

import org.springframework.stereotype.Service;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;


@Service
public class GameFrame extends Frame {
    private static final int BUFFER_TYPE = BufferedImage.TYPE_INT_RGB;

    private final GameFacade facade;
    private BufferedImage secondBuffer;
    private long time = 0;

    public GameFrame(WindowConfig config, KeyboardEventAdapter keyboardEventAdapter, GameFacade facade) {
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
        addKeyListener(keyboardEventAdapter);
        this.facade = facade;
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
        if (time > 10000000) {
            time = time % 10000000;
            facade.tick(0.1f);
        }
        Instant beforeRendering = Instant.now();
        secondBufferClearColor(0);
        Graphics2D bufferGraphics = secondBuffer.createGraphics();
        facade.drawLevel(bufferGraphics);
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.drawImage(secondBuffer, null, 0, 0);
        time += Duration.between(beforeRendering, Instant.now()).getNano();
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
