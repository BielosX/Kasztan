package org.level;

import java.awt.Graphics2D;

public class Obstacle extends GameObject implements Drawable {
    public Obstacle(float positionX, float positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawRect(Math.round(positionX), Math.round(positionY), width, height);
    }
}
