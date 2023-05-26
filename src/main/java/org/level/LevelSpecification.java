package org.level;

public record LevelSpecification(PlayerSpecification player) {
    public record PlayerSpecification(float x, float y) {}
}
