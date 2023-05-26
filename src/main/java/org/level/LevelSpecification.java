package org.level;

import java.util.List;

public record LevelSpecification(PlayerSpecification player, List<ObstacleSpecification> obstacles) {
    public record PlayerSpecification(float x, float y) {}
    public record ObstacleSpecification(float x, float y, int width, int height) {}
}
