package org.level;

import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.linear.RealVector;
import org.springframework.stereotype.Service;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LevelFacade implements Drawable {
    private final Player player;
    private final List<Obstacle> obstacles;


    @Override
    public void draw(Graphics2D graphics2D) {
        player.draw(graphics2D);
        obstacles.forEach(obstacle -> obstacle.draw(graphics2D));
    }

    public void tick(float deltaSeconds) {
        RealVector playerVelocity = player.getVelocity();
        obstacles.forEach(obstacle -> {
            Set<CollisionType> collisions = player.collide(obstacle);
            collisions.forEach(collisionType -> {
                float playerX = player.getPositionX();
                float playerY = player.getPositionY();
                if (collisionType == CollisionType.LEFT) {
                    playerVelocity.setEntry(0, 0.0);
                    playerX = obstacle.positionX + obstacle.getWidth() + 1.0f;
                }
                if (collisionType == CollisionType.RIGHT) {
                    playerVelocity.setEntry(0, 0.0);
                    playerX = obstacle.positionX - player.getWidth() - 1.0f;
                }
                if (collisionType == CollisionType.TOP) {
                    playerVelocity.setEntry(1, 0.0);
                    playerY = obstacle.positionY + obstacle.getHeight() + 1.0f;
                }
                if (collisionType == CollisionType.BOTTOM) {
                    playerVelocity.setEntry(1, 0.0);
                    playerY = obstacle.positionY - player.getHeight() - 1.0f;
                }
                player.setPositionX(playerX);
                player.setPositionY(playerY);
            });
        });
        player.setVelocity(playerVelocity);
        player.move(deltaSeconds);
        player.jump(deltaSeconds);
    }

    public void setPlayerVelocity(RealVector velocity) {
        player.setVelocity(velocity);
    }
}
