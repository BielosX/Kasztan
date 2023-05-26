package org.level;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.apache.commons.math3.linear.RealVector;

import java.awt.Graphics2D;

@AllArgsConstructor
public class Player implements Drawable, Collidable {
    private float positionX;
    private float positionY;
    @Setter
    private RealVector velocity;

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawRect(Math.round(positionX), Math.round(positionY), 30, 30);
    }

    public void move(float deltaSeconds) {
        positionX += velocity.getEntry(0) * deltaSeconds;
    }

    public void jump(float deltaSeconds) {
        positionY += velocity.getEntry(1) * deltaSeconds;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return null;
    }
}
