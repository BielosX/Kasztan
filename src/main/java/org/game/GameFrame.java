package org.game;

import org.springframework.stereotype.Service;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


@Service
public class GameFrame extends Frame {

    public GameFrame(WindowConfig config) {
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
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.drawString("Hello", 100, 100);
    }
}
