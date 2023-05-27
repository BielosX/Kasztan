package org.level;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.math3.linear.RealVector;

import java.awt.Graphics2D;
import java.util.List;

public class Player extends GameObject implements Drawable {
    @Setter
    @Getter
    private RealVector velocity;
    private final List<Obstacle> obstacles;

    public Player(float positionX,
                  float positionY,
                  int width,
                  int height,
                  RealVector velocity,
                  List<Obstacle> obstacles) {
        super(positionX, positionY, width, height);
        this.velocity = velocity;
        this.obstacles = obstacles;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawRect(Math.round(positionX), Math.round(positionY), width, height);
    }

    public void move(float deltaSeconds) {
        float currentPositionX = positionX;
        positionX += velocity.getEntry(0) * deltaSeconds;
        boolean anyCollision = obstacles.stream()
                .anyMatch(o -> o.collide(this));
        if (anyCollision) {
            positionX = currentPositionX;
        }
    }

    public void jump(float deltaSeconds) {
        float currentPositionY = positionY;
        positionY += velocity.getEntry(1) * deltaSeconds;
        boolean anyCollision = obstacles.stream()
                .anyMatch(o -> o.collide(this));
        if (anyCollision) {
            positionY = currentPositionY;
        }
    }
}
