package org.level;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.math3.linear.RealVector;

import java.awt.Graphics2D;

public class Player extends GameObject implements Drawable {
    @Setter
    @Getter
    private RealVector velocity;

    public Player(float positionX,
                  float positionY,
                  int width,
                  int height,
                  RealVector velocity) {
        super(positionX, positionY, width, height);
        this.velocity = velocity;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawRect(Math.round(positionX), Math.round(positionY), width, height);
    }

    public void move(float deltaSeconds) {
        positionX += velocity.getEntry(0) * deltaSeconds;
    }

    public void jump(float deltaSeconds) {
        positionY += velocity.getEntry(1) * deltaSeconds;
    }
}
