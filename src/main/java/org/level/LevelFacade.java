package org.level;

import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.linear.RealVector;
import org.springframework.stereotype.Service;

import java.awt.Graphics2D;
import java.util.List;

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
        player.move(deltaSeconds);
        player.jump(deltaSeconds);
    }

    public void setPlayerVelocity(RealVector velocity) {
        player.setVelocity(velocity);
    }

    public RealVector getPlayerVelocity() {
        return player.getVelocity();
    }
}
